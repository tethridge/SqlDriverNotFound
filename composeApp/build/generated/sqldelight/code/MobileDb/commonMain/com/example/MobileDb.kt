package com.example

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.example.composeApp.newInstance
import com.example.composeApp.schema
import kotlin.Unit

public interface MobileDb : Transacter {
  public val channelQueries: ChannelQueries

  public val deviceQueries: DeviceQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = MobileDb::class.schema

    public operator fun invoke(driver: SqlDriver): MobileDb = MobileDb::class.newInstance(driver)
  }
}
