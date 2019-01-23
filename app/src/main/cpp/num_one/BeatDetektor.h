#pragma once

/*
 * BeatDetektor.h
 *
 * BeatDetektor - CubicFX Visualizer Beat Detection & Analysis Algorithm
 *
 * Copyright (c) 2009 Charles J. Cliffe.
 *
 * BeatDetektor is distributed under the terms of the MIT License.
 * http://opensource.org/licenses/MIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */




/* 
 BeatDetektor class

 Theory:

 Trigger detection is performed using a trail of moving averages, 
 
 The FFT input is broken up into 128 ranges and averaged, each range has two moving 
 averages that tail each other at a rate of (1.0 / BD_DETECTION_RATE) seconds.  

 Each time the moving average for a range exceeds it's own tailing average by:

 (moving_average[range] * BD_DETECTION_FACTOR >= moving_average[range])

 if this is true there's a rising edge and a detection is flagged for that range. 
 Next a trigger gap test is performed between rising edges and timestamp recorded. 

 If the gap is larger than our BPM window (in seconds) then we can discard it and
 reset the timestamp for a new detection -- but only after checking to see if it's a 
 reasonable match for 2* the current detection in case it's only triggered every
 other beat. Gaps that are lower than the BPM window are ignored and the last 
 timestamp will not be reset.  

 Gaps that are within a reasonable window are run through a quality stage to determine 
 how 'close' they are to that channel's current prediction and are incremented or 
 decremented by a weighted value depending on accuracy. Repeated hits of low accuracy 
 will still move a value towards erroneous detection but it's quality will be lowered 
 and will not be eligible for the gap time quality draft.
 
 Once quality has been assigned ranges are reviewed for good match candidates and if 
 BD_MINIMUM_CONTRIBUTIONS or more ranges achieve a decent ratio (with a factor of 
 BD_QUALITY_TOLERANCE) of contribution to the overall quality we take them into the 
 contest round.  Note that the contest round  won't run on a given process() call if 
 the total quality achieved does not meet or exceed BD_QUALITY_TOLERANCE.
  
 Each time through if a select draft of BPM ranges has achieved a reasonable quality 
 above others it's awarded a value in the BPM contest.  The BPM contest is a hash 
 array indexed by an integer BPM value, each draft winner is awarded BD_QUALITY_REWARD.

 Finally the BPM contest is examined to determine a leader and all contest entries 
 are normalized to a total value of BD_FINISH_LINE, whichever range is closest to 
 BD_FINISH_LINE at any given point is considered to be the best guess however waiting 
 until a minimum contest winning value of about 20.0-25.0 will provide more accurate 
 results.  Note that the 20-25 rule may vary with lower and higher input ranges. 
 A winning value that exceeds 40 or hovers around 60 (the finish line) is pretty much
 a guaranteed match.


 Configuration Kernel Notes:

 The majority of the ratios and values have been reverse-engineered from my own  
 observation and visualization of information from various aspects of the detection 
 triggers; so not all parameters have a perfect definition nor perhaps the best value yet.
 However despite this it performs very well; I had expected several more layers 
 before a reasonable detection would be achieved. Comments for these parameters will be 
 updated as analysis of their direct effect is explored.


 Input Restrictions:

 bpm_maximum must be within the range of (bpm_minimum*2)-1
 i.e. minimum of 50 must have a maximum of 99 because 50*2 = 100

*/


#include <map>
#include <vector>
#include <math.h>

#define BD_DETECTION_RANGES 128
#define BD_DETECTION_RATE 12.0
#define BD_DETECTION_FACTOR 0.925

#define BD_QUALITY_TOLERANCE 0.96
#define BD_QUALITY_DECAY 0.95
#define BD_QUALITY_REWARD 7.0
#define BD_QUALITY_STEP 0.1
#define BD_FINISH_LINE 60.0
#define BD_MINIMUM_CONTRIBUTIONS 6

class BeatDetektor
{
public:
    float BPM_MIN;
    float BPM_MAX;

    BeatDetektor *src;

    float current_bpm;
    float winning_bpm;
    float winning_bpm_lo;
    float win_val;
    int win_bpm_int;
    float win_val_lo;
    int win_bpm_int_lo;

    float bpm_predict;

    bool is_erratic;
    float bpm_offset;
    float last_timer;
    float last_update;
    float total_time;

    float bpm_timer;
    int beat_counter;
    int half_counter;
    int quarter_counter;
    float detection_factor;
    //	float quality_minimum,
    float quality_reward,quality_decay,detection_rate,finish_line;
    int minimum_contributions;
    float quality_total, quality_avg, ma_quality_lo, ma_quality_total, ma_quality_avg, maa_quality_avg;

    // current average (this sample) for range n
    float a_freq_range[BD_DETECTION_RANGES];
    // moving average of frequency range n
    float ma_freq_range[BD_DETECTION_RANGES];
    // moving average of moving average of frequency range n
    float maa_freq_range[BD_DETECTION_RANGES];
    // timestamp of last detection for frequecy range n
    float last_detection[BD_DETECTION_RANGES];

    // moving average of gap lengths
    float ma_bpm_range[BD_DETECTION_RANGES];
    // moving average of moving average of gap lengths
    float maa_bpm_range[BD_DETECTION_RANGES];

    // range n quality attribute, good match = quality+, bad match = quality-, min = 0
    float detection_quality[BD_DETECTION_RANGES];

    // current trigger state for range n
    bool detection[BD_DETECTION_RANGES];

    std::map<int,float> bpm_contest;	// 1/10th
    std::map<int,float> bpm_contest_lo; // 1/1

#if DEVTEST_BUILD
    bool debugmode;
	std::map<int,int> contribution_counter;
#endif

    BeatDetektor(float BPM_MIN_in=100.0,float BPM_MAX_in=200.0, BeatDetektor *link_src = 0) :

            current_bpm(0.0),
            winning_bpm(0.0),
            win_val(0.0),
            win_bpm_int(0),
            win_val_lo(0.0),
            win_bpm_int_lo(0),

            bpm_predict(0),

            is_erratic(false),
            bpm_offset(0.0),
            last_timer(0.0),
            last_update(0.0),
            total_time(0.0),

            bpm_timer(0.0),
            beat_counter(0),
            half_counter(0),
            quarter_counter(0),
            src(link_src),

            //	quality_minimum(BD_QUALITY_MINIMUM),
            quality_reward(BD_QUALITY_REWARD),
            detection_rate(BD_DETECTION_RATE),
            finish_line(BD_FINISH_LINE),
            minimum_contributions(BD_MINIMUM_CONTRIBUTIONS),
            detection_factor(BD_DETECTION_FACTOR),
            quality_total(1.0),
            quality_avg(1.0),
            quality_decay(BD_QUALITY_DECAY),
            ma_quality_avg(0.001),
            ma_quality_lo(1.0),
            ma_quality_total(1.0)
#if DEVTEST_BUILD
    ,debugmode(false)
#endif

    {
        BPM_MIN = BPM_MIN_in;
        BPM_MAX = BPM_MAX_in;
        reset();
    }

    void reset(bool reset_freq=true)
    {
        for (int i = 0; i < BD_DETECTION_RANGES; i++)
        {
            //			ma_bpm_range[i] = maa_bpm_range[i] = 60.0/(float)(BPM_MIN + (1.0+sin(8.0*M_PI*((float)i/(float)BD_DETECTION_RANGES))/2.0)*((BPM_MAX-BPM_MIN)/2));
            ma_bpm_range[i] = maa_bpm_range[i] = 60.0/(float)(BPM_MIN+5)+ ((60.0/(float)(BPM_MAX-5)-60.0/(float)(BPM_MIN+5)) * ((float)i/(float)BD_DETECTION_RANGES));
            if (reset_freq)
            {
                a_freq_range[i] = ma_freq_range[i] = maa_freq_range[i] = 0;
            }
            last_detection[i] = 0;
            detection_quality[i] = 0;
            detection[i] = false;

        }

        total_time = 0;
        maa_quality_avg = 500.0;
        bpm_offset = bpm_timer = last_update = last_timer = winning_bpm = current_bpm = win_val = win_bpm_int = 0;
        bpm_contest.clear();
        bpm_contest_lo.clear();
#if DEVTEST_BUILD
        contribution_counter.clear();
#endif
    }

    void process(float timer_seconds, std::vector<float> &fft_data);
};
