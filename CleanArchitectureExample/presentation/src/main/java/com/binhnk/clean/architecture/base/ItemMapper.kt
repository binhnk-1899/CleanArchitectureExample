package com.binhnk.clean.architecture.base

import com.binhnk.clean.architecture.domain.model.Model

interface ItemMapper<M : Model, MI : ModelItem> {
    fun mapToPresentation(model: M): ModelItem

    fun mapToDomain(modelItem: MI): Model
}