FILESEXTRAPATHS_prepend := "${THISDIR}:"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "u-boot-mkimage-native dtc-native"

KV = "4.19"
LINUX_VERSION = "4.19.19"
LINUX_KERNEL_TYPE = "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

KBRANCH_axxiaarm = "v4.19/standard/axxia-dev/base-11e0e61"
SRCREV_machine_axxiaarm = "34de7a6754c853011d6a02bd1df3412b5e9791ca"
KBRANCH_axxiaarm64 = "v4.19/standard/axxia-dev/base-11e0e61"
SRCREV_machine_axxiaarm64 = "34de7a6754c853011d6a02bd1df3412b5e9791ca"

KMETA = ""

KREPO_KERNEL = "git://git@github.com/axxia/linux-yocto_private.git;protocol=ssh"
SRC_URI = "${KREPO_KERNEL};name=machine;branch=${KBRANCH} \
           file://fit "

require dt/dt-${KARCH}.inc

COMPATIBLE_MACHINE = "axxiaarm|axxiaarm64"
INSANE_SKIP_kernel-dev = "debug-files"

SMP ?= "yes"
POWER_MANAGEMENT ?= "low-power"
CHIPSET ?= "5500"
CHIPSET_LOWER = "${@d.getVar('CHIPSET').lower()}"
BIG_ENDIAN ?= "no"
DBG ?= "no"
TESTING ?= "no"
KERNEL_EXTRA_FEATURES = ""

KCONFIG_MODE = "--alldefconfig"
DEFCONFIG_RT = "${@oe.utils.conditional('LINUX_KERNEL_TYPE', 'preempt-rt', '_rt', '', d)}"
DEFCONFIG_DBG = "${@oe.utils.conditional('DBG', 'yes', '_dbg', '', d)}"
KBUILD_DEFCONFIG ?= "axxia_${CHIPSET_LOWER}${DEFCONFIG_TYPE}${DEFCONFIG_DBG}_defconfig"

do_kernel_metadata_prepend() {
    # If CHIPSET is changed and cleansstate is not ran, old defconfig remains in WORKDIR.
    # Remove it be sure the one from source tree si not skipped, but it's copied.
    rm -f "${WORKDIR}/defconfig"

    # Be sure XLF and X9 defconfigs are avaibale for axxiaarm build
    (
    cd ${S}/arch/arm64/configs
    for defconfig in $(ls axxia*); do
        ln -sf ../../arm64/configs/${defconfig} ${S}/arch/arm/configs/${defconfig}
    done
    )
}
