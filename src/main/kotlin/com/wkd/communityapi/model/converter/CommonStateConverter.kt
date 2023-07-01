package com.wkd.communityapi.model.converter

import com.wkd.communityapi.model.common.CommonState
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class CommonStateConverter : AttributeConverter<CommonState, Int> {
    override fun convertToDatabaseColumn(attribute: CommonState?): Int = attribute!!.value

    override fun convertToEntityAttribute(dbData: Int?): CommonState = CommonState.parse(dbData!!)
}