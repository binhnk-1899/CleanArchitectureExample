package com.binhnk.clean.architecture.data.base

import com.binhnk.clean.architecture.domain.model.Model


interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}