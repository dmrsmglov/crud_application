package ru.damir.server.domain

import javax.persistence.Entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Post (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var title: String,
    var content: String
)
