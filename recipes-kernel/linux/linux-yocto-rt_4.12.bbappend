FILESEXTRAPATHS_prepend := "\
${THISDIR}\
:${THISDIR}/conf/yocto-${KV}/${MACHINE}/common\
:${THISDIR}/conf/yocto-${KV}/${MACHINE}/${LINUX_KERNEL_TYPE}:"

DEPENDS += "u-boot-mkimage-native dtc-native"

KV = "4.12"
LINUX_VERSION = "4.12.16"
KBRANCH = "standard/preempt-rt/axxia/base"
SRCREV_machine = "51c45f6489df4cdbce78e9c90b8a26fa7e7c875c"
SRCREV_meta = "3574bb061c1bfbdcf4df8308870c03f88ef0788f"

SRC_URI_append = " file://fit"

require dt/dt-${KARCH}.inc
require frags/frags-${KARCH}.inc

COMPATIBLE_MACHINE_append = "|axxiaarm|axxiaarm64"
INSANE_SKIP_kernel-dev = "debug-files"

SMP ?= "yes"
POWER_MANAGEMENT ?= "low-power"
CHIPSET ?= "5500"
BIG_ENDIAN ?= "no"
DBG ?= "no"
TESTING ?= "no"
KERNEL_EXTRA_FEATURES = ""
