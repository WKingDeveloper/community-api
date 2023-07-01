package com.wkd.communityapi.model.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@MappedSuperclass
abstract class EntityBase {
    @Version
    private val version: Long? = null

    @field:CreationTimestamp
    @Column(
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
        insertable = false,
        updatable = false
    )
    val createdAt: Timestamp? = null

    @field:UpdateTimestamp
    @Column(
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
        insertable = false,
        updatable = false
    )
    val updatedAt: Timestamp? = null

    /**
     * 신규 생성 여부
     */
    fun isFresh(): Boolean {
        return version == null
    }
}
