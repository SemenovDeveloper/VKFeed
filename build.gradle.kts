// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
}

vkidManifestPlaceholders {
    val clientId = "53716758"
    val clientSecret = "HzltN3YftmTHFZfFCyRc"

    init(
        clientId = clientId,
        clientSecret = clientSecret,
    )

    vkidRedirectHost = "vk.com"
    vkidRedirectScheme = "vk53716758"
    vkidClientId = clientId
    vkidClientSecret = clientSecret
}