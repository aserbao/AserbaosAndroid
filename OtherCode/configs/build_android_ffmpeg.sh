#!/usr/bin/env bash
#Change NDK to your Android NDK location
CWD=`pwd`
NDK=/Users/aserbao/Library/Android/sdk/ndk/android-ndk-r13b
SOURCE=$CWD/ffmpeg-3.3.9
OUTPUT_DIR=$CWD/build/android/ffmpeg
X264_DIR=$CWD/build/android/x264
PNG_DIR=$CWD/build/android/png
ACC_DIR=
TEMP_DIR="tmp/android/ffmpeg"
ARCHS="armeabi-v7a arm64-v8a"
#armeabi-v7a arm64-v8a x86_64 x86

MODULES="\
	--enable-gpl
	--enable-libx264 \
	--enable-hwaccel=h264_vaapi \
	--enable-hwaccel=h264_vaapi \
	--enable-hwaccel=h264_dxva2 \
	--enable-hwaccel=mpeg4_vaapi \
	--enable-hwaccels \
	--enable-jni \
	--enable-mediacodec \
	--enable-gpl \
	--enable-nonfree \
	--enable-version3 \
	--enable-small \
	--enable-shared \
	--disable-vda \
	--disable-iconv \
	--disable-encoders \
	--enable-encoder=libx264 \
	--enable-encoder=mpeg4 \
	--enable-encoder=aac \
	--enable-encoder=gif \
	--enable-encoder=png \
	--enable-encoder=pcm_s16le \
	--disable-muxers \
	--enable-muxer=mp4 \
	--enable-muxer=gif \
	--enable-muxer=mp3 \
	--enable-muxer=wav \
	--enable-muxer=mov \
	--enable-muxer=pcm_s16le \
	--disable-decoders \
	--enable-decoder=aac \
	--enable-decoder=aac_latm \
	--enable-decoder=h264 \
	--enable-decoder=mpeg4 \
	--enable-decoder=mjpeg \
	--enable-decoder=png \
	--enable-decoder=gif \
	--enable-decoder=mp3 \
	--enable-decoder=pcm_s16le \
	--disable-demuxers \
	--enable-demuxer=h264 \
	--enable-demuxer=avi \
	--enable-demuxer=mpc \
	--enable-demuxer=mov \
	--enable-demuxer=image2 \
	--enable-demuxer=concat \
	--enable-demuxer=aac \
	--enable-demuxer=mp3 \
	--enable-demuxer=wav \
	--enable-demuxer=gif \
	--enable-demuxer=pcm_s16le \
	--disable-parsers \
	--enable-parser=aac \
	--enable-parser=ac3 \
	--enable-parser=h264 \
	--disable-protocols \
	--enable-protocol=file \
	--disable-bsfs \
	--enable-bsf=aac_adtstoasc \
	--enable-bsf=h264_mp4toannexb \
	--disable-indevs \
	--enable-zlib \
	--enable-neon \
	--enable-asm \
	--enable-shared \
	--enable-avdevice"

GENERAL="\
	--logfile=conflog.txt \
	--target-os=android \
	--enable-small \
	--enable-cross-compile \
	--enable-runtime-cpudetect \
	--enable-shared \
	--disable-static \
	--disable-ffprobe \
	--disable-ffplay \
	--disable-ffmpeg \
	--disable-ffserver \
	--disable-symver \
	--disable-stripping \
	--disable-doc \
	--disable-programs \
	--disable-debug"

CFLAGS="-O3 -Wall -pipe \
    -std=c99 \
    -ffast-math \
    -fstrict-aliasing -Werror=strict-aliasing \
    -Wno-psabi -Wa,--noexecstack \
    -DANDROID -DNDEBUG"

LFLAGS=

for ARCH in $ARCHS; do
	PLATFORM=
	TOOLCHAIN=
	ARCH_FLAGS=
	EXTRA_CFLAGS=
	EXTRA_LDFLAGS=
	echo "building $ARCH..."
	mkdir -p "$TEMP_DIR/$ARCH"
	cd "$TEMP_DIR/$ARCH"
	case $ARCH in
	armeabi-v7a)
		PLATFORM=$NDK/platforms/android-19/arch-arm/
		TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi

		ARCH_FLAGS="$ARCH_FLAGS --arch=arm --cpu=cortex-a8"
		ARCH_FLAGS="$ARCH_FLAGS --enable-neon"
		ARCH_FLAGS="$ARCH_FLAGS --enable-thumb"

		EXTRA_CFLAGS="$EXTRA_CFLAGS -fPIC -ffunction-sections -funwind-tables -fstack-protector -march=armv7-a -mfloat-abi=softfp -mfpu=vfpv3-d16 -fomit-frame-pointer -fstrict-aliasing -funswitch-loops -finline-limit=300"
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS"
	;;
	arm64-v8a)
		PLATFORM=$NDK/platforms/android-21/arch-arm64/
		TOOLCHAIN=$NDK/toolchains/aarch64-linux-android-4.9/prebuilt/darwin-x86_64/bin/aarch64-linux-android

		ARCH_FLAGS="$ARCH_FLAGS --arch=aarch64 --enable-yasm"

		EXTRA_CFLAGS="$EXTRA_CFLAGS"
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS"
	;;
	x86_64)
		PLATFORM=$NDK/platforms/android-21/arch-x86_64/
		TOOLCHAIN=$NDK/toolchains/x86_64-4.9/prebuilt/darwin-x86_64/bin/x86_64-linux-android

		ARCH_FLAGS="$ARCH_FLAGS --arch=x86_64 --enable-yasm"

		EXTRA_CFLAGS="$EXTRA_CFLAGS -march=x86-64 -msse4.2 -mpopcnt -m64 -mtune=intel"
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS"
	;;
	x86)
		PLATFORM="$NDK/platforms/android-19/arch-x86/"
		TOOLCHAIN="$NDK/toolchains/x86-4.9/prebuilt/darwin-x86_64/bin/i686-linux-android"

		ARCH_FLAGS="$ARCH_FLAGS --arch=x86 --cpu=i686 --enable-yasm"

		EXTRA_CFLAGS="$EXTRA_CFLAGS -Dipv6mr_interface=ipv6mr_ifindex -fasm -fno-short-enums -fno-strict-aliasing -fomit-frame-pointer -march=i686 -msse3 -ffast-math -mfpmath=sse -mtune=intel -m32"
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS"
	;;
	esac

	ARCH_FLAGS="$ARCH_FLAGS --cc=$TOOLCHAIN-gcc"
	ARCH_FLAGS="$ARCH_FLAGS --cross-prefix=$TOOLCHAIN-"
	ARCH_FLAGS="$ARCH_FLAGS --nm=$TOOLCHAIN-nm"
	ARCH_FLAGS="$ARCH_FLAGS --prefix=$OUTPUT_DIR/$ARCH"
	ARCH_FLAGS="$ARCH_FLAGS --sysroot=$PLATFORM"

	if [ "$X264_DIR" ] ; then
		EXTRA_CFLAGS="$EXTRA_CFLAGS -I$X264_DIR/$ARCH/include"
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS -L$X264_DIR/$ARCH/lib"
	fi
	if [ "$PNG_DIR" ] ; then
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS -L$PNG_DIR/$ARCH/lib"
	fi
	if [ "$ACC_DIR" ] ; then
		EXTRA_CFLAGS="$EXTRA_CFLAGS -I$ACC_DIR/$ARCH/include"
		EXTRA_LDFLAGS="$EXTRA_LDFLAGS -L$ACC_DIR/$ARCH/lib"
	fi

	LFLAGS="-lx264 -Wl,-rpath-link=$PLATFORM/usr/lib -L$PLATFORM/usr/lib -nostdlib -lc -lm -ldl -llog"

	echo "platform:$PLATFORM"
	echo "toolchain:$TOOLCHAIN"
	echo "arch_flags:$ARCH_FLAGS"
	echo "cflags:$EXTRA_CFLAGS"
	echo "lflags:$EXTRA_LDFLAGS"

	cd $SOURCE
	TMPDIR=${TMPDIR/%\/} \
	./configure \
		${GENERAL} \
		${MODULES} \
		${ARCH_FLAGS} \
		--extra-cflags="$EXTRA_CFLAGS" \
		--extra-ldflags="$EXTRA_LDFLAGS" \
		--extra-libs="-lgcc -lpng -lm" || exit 1

	make clean
	make -j16
	make install
	cd $CWD
done

echo android build finished