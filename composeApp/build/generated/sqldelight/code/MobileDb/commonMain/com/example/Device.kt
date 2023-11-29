package com.example

import kotlin.Long
import kotlin.String

public data class Device(
  public val id: Long,
  public val device_id: String,
  public val name: String,
  public val label: String?,
  public val ibot_node: String?,
  public val session_id: String?,
  public val device_status: String?,
  public val active_channel: String?,
  public val last_connect_timestamp: String?,
)
