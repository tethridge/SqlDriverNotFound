package com.example

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class ChannelQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectByDeviceId(device_id: String, mapper: (
    id: Long,
    channel_id: String,
    device_id: String,
    name: String,
    type: String,
    type_id: String,
    catalog_type: String?,
    force_switch_on_page: Long?,
    is_hidden: Long?,
    color: String?,
  ) -> T): Query<T> = SelectByDeviceIdQuery(device_id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getLong(7),
      cursor.getLong(8),
      cursor.getString(9)
    )
  }

  public fun selectByDeviceId(device_id: String): Query<Channel> = selectByDeviceId(device_id) { id,
      channel_id, device_id_, name, type, type_id, catalog_type, force_switch_on_page, is_hidden,
      color ->
    Channel(
      id,
      channel_id,
      device_id_,
      name,
      type,
      type_id,
      catalog_type,
      force_switch_on_page,
      is_hidden,
      color
    )
  }

  public fun <T : Any> selectByChannelId(channel_id: String, mapper: (
    id: Long,
    channel_id: String,
    device_id: String,
    name: String,
    type: String,
    type_id: String,
    catalog_type: String?,
    force_switch_on_page: Long?,
    is_hidden: Long?,
    color: String?,
  ) -> T): Query<T> = SelectByChannelIdQuery(channel_id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getLong(7),
      cursor.getLong(8),
      cursor.getString(9)
    )
  }

  public fun selectByChannelId(channel_id: String): Query<Channel> = selectByChannelId(channel_id) {
      id, channel_id_, device_id, name, type, type_id, catalog_type, force_switch_on_page,
      is_hidden, color ->
    Channel(
      id,
      channel_id_,
      device_id,
      name,
      type,
      type_id,
      catalog_type,
      force_switch_on_page,
      is_hidden,
      color
    )
  }

  public fun <T : Any> selectVisibleChannels(device_id: String, mapper: (
    id: Long,
    channel_id: String,
    device_id: String,
    name: String,
    type: String,
    type_id: String,
    catalog_type: String?,
    force_switch_on_page: Long?,
    is_hidden: Long?,
    color: String?,
  ) -> T): Query<T> = SelectVisibleChannelsQuery(device_id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getLong(7),
      cursor.getLong(8),
      cursor.getString(9)
    )
  }

  public fun selectVisibleChannels(device_id: String): Query<Channel> =
      selectVisibleChannels(device_id) { id, channel_id, device_id_, name, type, type_id,
      catalog_type, force_switch_on_page, is_hidden, color ->
    Channel(
      id,
      channel_id,
      device_id_,
      name,
      type,
      type_id,
      catalog_type,
      force_switch_on_page,
      is_hidden,
      color
    )
  }

  public fun <T : Any> select(
    device_id: String,
    name: String,
    mapper: (
      id: Long,
      channel_id: String,
      device_id: String,
      name: String,
      type: String,
      type_id: String,
      catalog_type: String?,
      force_switch_on_page: Long?,
      is_hidden: Long?,
      color: String?,
    ) -> T,
  ): Query<T> = SelectQuery(device_id, name) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getLong(7),
      cursor.getLong(8),
      cursor.getString(9)
    )
  }

  public fun select(device_id: String, name: String): Query<Channel> = select(device_id, name) { id,
      channel_id, device_id_, name_, type, type_id, catalog_type, force_switch_on_page, is_hidden,
      color ->
    Channel(
      id,
      channel_id,
      device_id_,
      name_,
      type,
      type_id,
      catalog_type,
      force_switch_on_page,
      is_hidden,
      color
    )
  }

  public fun insert(
    channel_id: String,
    device_id: String,
    name: String,
    type: String,
    type_id: String,
    catalog_type: String?,
    force_switch_on_page: Long?,
    is_hidden: Long?,
    color: String?,
  ) {
    driver.execute(-200_676_403, """
        |INSERT INTO Channel (channel_id, device_id, name, type, type_id, catalog_type, force_switch_on_page, is_hidden, color)
        |VALUES (?,?,?,?,?,?,?,?, ?)
        """.trimMargin(), 9) {
          bindString(0, channel_id)
          bindString(1, device_id)
          bindString(2, name)
          bindString(3, type)
          bindString(4, type_id)
          bindString(5, catalog_type)
          bindLong(6, force_switch_on_page)
          bindLong(7, is_hidden)
          bindString(8, color)
        }
    notifyQueries(-200_676_403) { emit ->
      emit("Channel")
    }
  }

  public fun deleteByDeviceId(device_id: String) {
    driver.execute(1_436_449_703, """
        |DELETE FROM Channel
        |WHERE device_id = ?
        """.trimMargin(), 1) {
          bindString(0, device_id)
        }
    notifyQueries(1_436_449_703) { emit ->
      emit("Channel")
    }
  }

  public fun deleteAll() {
    driver.execute(269_575_778, """DELETE FROM Channel""", 0)
    notifyQueries(269_575_778) { emit ->
      emit("Channel")
    }
  }

  private inner class SelectByDeviceIdQuery<out T : Any>(
    public val device_id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Channel", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Channel", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_010_501_192, """
    |SELECT *
    |FROM Channel
    |WHERE device_id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, device_id)
    }

    override fun toString(): String = "Channel.sq:selectByDeviceId"
  }

  private inner class SelectByChannelIdQuery<out T : Any>(
    public val channel_id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Channel", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Channel", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(175_781_559, """
    |SELECT *
    |FROM Channel
    |WHERE channel_id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, channel_id)
    }

    override fun toString(): String = "Channel.sq:selectByChannelId"
  }

  private inner class SelectVisibleChannelsQuery<out T : Any>(
    public val device_id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Channel", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Channel", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-399_876_558, """
    |SELECT *
    |FROM Channel
    |WHERE device_id = ? AND is_hidden = FALSE
    """.trimMargin(), mapper, 1) {
      bindString(0, device_id)
    }

    override fun toString(): String = "Channel.sq:selectVisibleChannels"
  }

  private inner class SelectQuery<out T : Any>(
    public val device_id: String,
    public val name: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Channel", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Channel", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(77_094_416, """
    |SELECT *
    |FROM Channel
    |WHERE device_id = ? AND name = ?
    """.trimMargin(), mapper, 2) {
      bindString(0, device_id)
      bindString(1, name)
    }

    override fun toString(): String = "Channel.sq:select"
  }
}
