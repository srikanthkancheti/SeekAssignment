package com.seek.assignment.core.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.random.Random
import kotlin.random.nextULong

@Serializable(with = UUID.UUIDSerializer::class)
class UUID(private val msb: ULong, private val lsb: ULong) : Comparable<UUID> {
    override fun toString() = buildString {
        val chars = msb.toString(16).padStart(16, '0') + lsb.toString(16).padStart(16, '0')
        append(chars.substring(0, 8))
        append('-')
        append(chars.substring(8, 12))
        append('-')
        append(chars.substring(12, 16))
        append('-')
        append(chars.substring(16, 20))
        append('-')
        append(chars.substring(20))
    }

    override fun compareTo(other: UUID): Int = when {
        this.msb < other.msb -> -1
        this.msb > other.msb -> 1
        this.lsb < other.lsb -> -1
        this.lsb > other.lsb -> 1
        else -> 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as UUID

        if (msb != other.msb) return false
        if (lsb != other.lsb) return false

        return true
    }

    override fun hashCode(): Int {
        var result = msb.hashCode()
        result = 31 * result + lsb.hashCode()
        return result
    }

    companion object {

        fun randomUUID(random: Random = Random.Default): UUID {
            val msb = random.nextULong() and 0xffffffffffff0fffUL or 0x0000000000004000UL
            val lsb = random.nextULong() and 0x3fffffffffffffffUL or 0x8000000000000000UL
            return UUID(msb, lsb)
        }

        fun fromString(name: String): UUID {
            if (name.count { it == '-' } != 4) throw IllegalArgumentException("Invalid UUID string")
            val (mostHigh, mostMid, mostLow, leastHigh, leastLow) = name.split('-')
            return UUID(
                (mostHigh + mostMid + mostLow).toULong(16),
                (leastHigh + leastLow).toULong(16)
            )
        }
    }

    object UUIDSerializer : KSerializer<UUID> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: UUID) {
            encoder.encodeString(value.toString())
        }

        override fun deserialize(decoder: Decoder): UUID = fromString(decoder.decodeString())
    }
}