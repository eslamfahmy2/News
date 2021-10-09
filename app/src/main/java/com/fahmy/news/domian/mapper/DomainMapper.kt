package com.fahmy.news.domian.mapper

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

}