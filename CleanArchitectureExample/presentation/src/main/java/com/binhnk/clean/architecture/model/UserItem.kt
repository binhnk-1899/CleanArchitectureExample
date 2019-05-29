package com.binhnk.clean.architecture.model

import com.binhnk.clean.architecture.base.ItemMapper
import com.binhnk.clean.architecture.base.ModelItem
import com.binhnk.clean.architecture.domain.model.User

data class UserItem(
    var id: Int,
    var email: String,
    var firstName: String,
    var lastName: String,
    var avatar: String,
    var page: Int,
    var addedInDB: Boolean = false
) : ModelItem()

class UserItemMapper : ItemMapper<User, UserItem> {
    override fun mapToPresentation(model: User) = UserItem(
        id = model.id,
        email = model.email,
        firstName = model.firstName,
        lastName = model.lastName,
        avatar = model.avatar,
        page = model.page,
        addedInDB = model.addedInDB
    )

    override fun mapToDomain(modelItem: UserItem) = User(
        id = modelItem.id,
        email = modelItem.email,
        firstName = modelItem.firstName,
        lastName = modelItem.lastName,
        avatar = modelItem.avatar,
        page = modelItem.page,
        addedInDB = modelItem.addedInDB
    )

}