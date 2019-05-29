package com.binhnk.clean.architecture.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.binhnk.clean.architecture.data.base.EntityMapper
import com.binhnk.clean.architecture.data.base.ModelEntity
import com.binhnk.clean.architecture.domain.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") @SerializedName("id") @Expose var id: Int,
    @ColumnInfo(name = "email") @SerializedName("email") @Expose var email: String,
    @ColumnInfo(name = "first_name") @SerializedName("first_name") @Expose var firstName: String,
    @ColumnInfo(name = "last_name") @SerializedName("last_name") @Expose var lastName: String,
    @ColumnInfo(name = "avatar") @SerializedName("avatar") @Expose var avatar: String,
    @ColumnInfo(name = "page") var page: Int = 0,
    var addedInDB: Boolean = false
) : ModelEntity()

class UserEntityMapper : EntityMapper<User, UserEntity> {
    override fun mapToDomain(entity: UserEntity): User = User(
        id = entity.id,
        email = entity.email,
        firstName = entity.firstName,
        lastName = entity.lastName,
        avatar = entity.avatar,
        page = entity.page,
        addedInDB = entity.addedInDB
    )

    override fun mapToEntity(model: User): UserEntity = UserEntity(
        id = model.id,
        email = model.email,
        firstName = model.firstName,
        lastName = model.lastName,
        avatar = model.avatar,
        page = model.page,
        addedInDB = model.addedInDB
    )

}