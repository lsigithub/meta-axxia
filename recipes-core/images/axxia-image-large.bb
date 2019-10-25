DESCRIPTION = "A large and complete image including all Axxia \
required packages."

require recipes-core/images/core-image-minimal-dev.bb
require axxia-image.inc

IMAGE_INSTALL = " \
acl \
at \
attr \
autoconf \
autofs \
base-files \
base-passwd \
bash \
bc \
bind \
bind-utils \
bison \
bluez5 \
boost \
boost-dev \
busybox \
busybox-udhcpc \
bzip2 \
coreutils \
cpio \
cracklib \
cronie \
curl \
db \
dbus \
dbus-glib \
dhcp-client \
dhcp-server \
diffutils \
dmidecode \
e2fsprogs \
e2fsprogs-badblocks \
e2fsprogs-e2fsck \
e2fsprogs-mke2fs \
e2fsprogs-resize2fs \
e2fsprogs-tune2fs \
ed \
elfutils \
ethtool \
expat \
expect \
file \
findutils \
flac \
flex \
gawk \
gdb \
gdbserver \
gettext \
gfortran \
gfortran-symlinks \
glib-2.0 \
gmp \
gnutls \
grep \
groff \
gzip \
hdparm \
icu \
inetutils \
inetutils-telnetd \
init-ifupdown \
initscripts \
iproute2 \
iptables \
iputils \
irda-utils \
iw \
kdump \
kernel-dev \
kernel-devsrc \
kernel-modules \
kexec \
kexec-tools \
kmod \
ldd \
less \
libaio \
libasan \
libubsan \
libcap \
libcheck \
libdaemon \
libevent \
libffi \
libgcrypt \
libgfortran \
libgfortran-dev \
libgfortran-dbg \
libgpg-error \
libice \
libkmod \
libnl \
libnss-mdns \
libnss-nis \
libogg \
libpython2 \
libpam \
libpcap \
libpcap-dev \
libpcre \
libsamplerate0 \
libsm \
libsndfile1 \
libtasn1 \
libtirpc \
libtool \
libudev \
libunwind \
libusb1 \
libusb-compat \
libxau \
libxcb \
libxdmcp \
lighttpd \
logrotate \
lrzsz \
ltp \
lttng-modules \
lttng-modules-dev \
lttng-tools \
lttng-tools-dev \
lttng-ust \
lttng-ust-dev \
lvm2 \
lzo \
man \
man-pages \
mdadm \
modutils-initscripts \
msmtp \
mtd-utils \
ncurses \
netbase \
net-tools \
nfs-utils \
nfs-utils-client \
${@oe.utils.conditional('MACHINE', 'axxiaarm64', 'numactl', '', d)} \
openssh \
openssh-sftp \
openssh-sftp-server \
openssl \
opkg \
opkg-arch-config \
packagegroup-core-boot \
packagegroup-core-buildessential \
parted \
pciutils \
${@oe.utils.conditional('MACHINE', 'axxiaarm64', 'perf', '', d)} \
perl \
perl-module-bigint \
pkgconfig \
popt \
ppp \
procps \
psmisc \
python-cffi \
python-core \
python-dev \
python-distutils \
python-modules \
python-netserver \
python-nose \
quota \
readline \
rpcbind \
rpcsvc-proto \
rpm \
run-postinsts \
sed \
setserial \
shadow \
shadow-securetty \
sqlite3 \
strace \
strongswan \
sudo \
sysfsutils \
sysklogd \
tar \
tcl \
tcpdump \
tcp-wrappers \
telnetd \
time \
tmux \
tzdata \
udev \
udev-extraconf \
unzip \
update-rc.d \
usbutils \
util-linux \
util-linux-libblkid \
util-linux-libuuid \
util-macros \
vlan \
watchdog \
wget \
which \
ypbind-mt \
yp-tools \
yp-tools-dev \
zip \
zlib \
${LXC_SUPPORT} \
${@oe.utils.conditional('KERNEL_MAJOR_PROVIDER', 'linux-yocto', \
			'${YOCTO_ALTERNATIVE_KERNELS}', '', d)} \
${@oe.utils.conditional('KERNEL_MAJOR_PROVIDER', 'linux-axxia', \
			'${AXXIA_ALTERNATIVE_KERNELS}', '', d)} \
"

LXC_SUPPORT ?= " \
cgroup-lite \
gnupg \
libvirt \
libvirt-libvirtd \
lxc \
lxc-networking \
lxc-templates \
xz"

KERNEL_MAJOR_PROVIDER = \
"${@'-'.join(d.getVar('PREFERRED_PROVIDER_virtual/kernel').split('-')[0:2])}"

YOCTO_ALTERNATIVE_KERNELS ?= " \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-yocto', '',    'kernel-linux-yocto', d)} \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-yocto-rt', '', 'kernel-linux-yocto-rt', d)} \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-yocto-debug', '',    'kernel-linux-yocto-debug', d)} \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-yocto-rt-debug', '', 'kernel-linux-yocto-rt-debug', d)} \
"

AXXIA_ALTERNATIVE_KERNELS ?= " \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-axxia', '',    'kernel-linux-axxia', d)} \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-axxia-rt', '', 'kernel-linux-axxia-rt', d)} \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-axxia-debug', '',    'kernel-linux-axxia-debug', d)} \
${@oe.utils.conditional('PREFERRED_PROVIDER_virtual/kernel', \
	'linux-axxia-rt-debug', '', 'kernel-linux-axxia-rt-debug', d)} \
"

IMAGE_FEATURES += " \
dev-pkgs \
tools-sdk \
tools-debug \
eclipse-debug \
${@oe.utils.conditional('MACHINE', 'axxiaarm64', 'tools-profile', '', d)} \
tools-testapps \
debug-tweaks \
ssh-server-openssh"

SDKIMAGE_FEATURES = "dev-pkgs dbg-pkgs staticdev-pkgs"

TOOLCHAIN_TARGET_TASK_append = " \
libc-staticdev \
binutils-staticdev \
elfutils-dev \
libelf \
libnl-dev \
libunwind-dev \
${@oe.utils.conditional('MACHINE', 'axxiaarm64', 'numactl-dev', '', d)} \
python-dev \
slang-dev \
strace-dev \
systemtap \
systemtap-dev \
xz-dev"

TOOLCHAIN_HOST_TASK_append = " \
nativesdk-bison \
nativesdk-python-dev"
