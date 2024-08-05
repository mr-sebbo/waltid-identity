package id.walt.webwallet.config

import id.walt.crypto.keys.KeyGenerationRequest
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class RegistrationDefaultsConfig(
    val defaultKeyConfig: KeyGenerationRequest?,
    val defaultDidConfig: DidMethodConfig?,
) : WalletConfig() {

    fun isEmpty() = defaultKeyConfig == null || defaultDidConfig == null

    @Serializable
    data class DidMethodConfig(
        val method: String = "jwk",
        val config: Map<String, JsonPrimitive> = emptyMap(),
    )

    @Transient
    val didMethod = defaultDidConfig?.method

    @Transient
    val didConfig: Map<String, JsonPrimitive>? = defaultDidConfig?.config

    companion object {
        val Default = RegistrationDefaultsConfig(KeyGenerationRequest(), DidMethodConfig())
    }
}