package com.example

import kotlin.Long
import kotlin.String

public data class Channel(
  public val id: Long,
  public val channel_id: String,
  public val device_id: String,
  public val name: String,
  public val type: String,
  public val type_id: String,
  public val catalog_type: String?,
  public val force_switch_on_page: Long?,
  public val is_hidden: Long?,
  public val color: String?,
)
