package com.example.composeApp

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.example.ChannelQueries
import com.example.DeviceQueries
import com.example.MobileDb
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<MobileDb>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = MobileDbImpl.Schema

internal fun KClass<MobileDb>.newInstance(driver: SqlDriver): MobileDb = MobileDbImpl(driver)

private class MobileDbImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), MobileDb {
  override val channelQueries: ChannelQueries = ChannelQueries(driver)

  override val deviceQueries: DeviceQueries = DeviceQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE Channel (
          |id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |channel_id TEXT NOT NULL,
          |device_id TEXT NOT NULL,
          |name TEXT NOT NULL,
          |type TEXT NOT NULL,
          |type_id TEXT NOT NULL,
          |catalog_type TEXT,
          |force_switch_on_page INTEGER DEFAULT 0,
          |is_hidden INTEGER DEFAULT 0,
          |color TEXT
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE Device (
          |id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |device_id TEXT NOT NULL UNIQUE,
          |name TEXT NOT NULL,
          |label TEXT,
          |ibot_node TEXT,
          |session_id TEXT,
          |device_status TEXT,
          |active_channel TEXT,
          |last_connect_timestamp TEXT
          |)
          """.trimMargin(), 0)
      driver.execute(null, "CREATE INDEX Channel_device_id ON Channel(device_id)", 0)
      driver.execute(null, "CREATE INDEX Channel_channel_id ON Channel(channel_id)", 0)
      driver.execute(null, "CREATE INDEX Channel_device_id_name ON Channel(device_id, name)", 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
