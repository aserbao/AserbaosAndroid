#!/bin/bash
NDK_ROOT=/Users/aserbao/Library/Android/sdk/ndk/android-ndk-r13b
PREBUILT=$NDK_ROOT/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64
PLATFORM=$NDK_ROOT/platforms/android-19/arch-arm/
CONFIGURE_FLAGS="--enable-static --enable-shared "
SOURCE=/Users/aserbao/git/build-ffmpeg-for-android/lame-3.99.5
PREFIX=/Users/aserbao/git/build-ffmpeg-for-android/build/android/lame/armeabi-v7a
HOST="arm-linux"
CROSS_PREFIX=arm-linux-androideabi-

echo "platform:$PLATFORM"
echo "toolchains:$TOOLCHAIN"
echo "cross-prefix:$CROSS_PREFIX"
echo "prefix:$PREFIX"
echo "host:$HOST"

TMPDIR=${TMPDIR/%\/} $SOURCE/configure \
  ${CONFIGURE_FLAGS} \
  --prefix=$PREFIX \
  --host=$HOST \
  --cross-prefix=$CROSS_PREFIX \
  --sysroot=$PLATFORM || exit 1
make -j8 install
