#
# Copyright (C) 2012 The CyanogenMod Project
# Copyright (C) 2013 The OmniROM Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Inherit Omni GSM telephony parts
$(call inherit-product, vendor/omni/config/gsm.mk)

# Inherit some common Omni stuff
$(call inherit-product, vendor/omni/config/common.mk)

# Inherit from the common Open Source product configuration
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit more device specific configurations
$(call inherit-product, device/samsung/i9100g/device.mk)

# Discard inherited values and use our own instead.
PRODUCT_MODEL := GT-I9100G
PRODUCT_BRAND := samsung
PRODUCT_NAME := omni_i9100g
PRODUCT_DEVICE := i9100g
PRODUCT_MANUFACTURER := samsung

#Set build fingerprint / ID / Prduct Name ect.
PRODUCT_BUILD_PROP_OVERRIDES += \
    PRODUCT_NAME=GT-I9100G \
    TARGET_DEVICE=GT-I9100G \
    PRIVATE_BUILD_DESC="GT-I9100G-user 4.1.2 JZO54K I9100GDXLS8 release-keys" \
    BUILD_FINGERPRINT="samsung/GT-I9100G/GT-I9100G:4.1.2/JZO54K/I9100GDXLS8:user/release-keys"
