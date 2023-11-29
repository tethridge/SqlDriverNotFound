package com.example

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class DeviceQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAll(mapper: (
    id: Long,
    device_id: String,
    name: String,
    label: String?,
    ibot_node: String?,
    session_id: String?,
    device_status: String?,
    active_channel: String?,
    last_connect_timestamp: String?,
  ) -> T): Query<T> = Query(2_119_975_012, arrayOf("Device"), driver, "Device.sq", "selectAll",
      "SELECT * FROM Device") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun selectAll(): Query<Device> = selectAll { id, device_id, name, label, ibot_node,
      session_id, device_status, active_channel, last_connect_timestamp ->
    Device(
      id,
      device_id,
      name,
      label,
      ibot_node,
      session_id,
      device_status,
      active_channel,
      last_connect_timestamp
    )
  }

  public fun <T : Any> selectById(id: Long, mapper: (
    id: Long,
    device_id: String,
    name: String,
    label: String?,
    ibot_node: String?,
    session_id: String?,
    device_status: String?,
    active_channel: String?,
    last_connect_timestamp: String?,
  ) -> T): Query<T> = SelectByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun selectById(id: Long): Query<Device> = selectById(id) { id_, device_id, name, label,
      ibot_node, session_id, device_status, active_channel, last_connect_timestamp ->
    Device(
      id_,
      device_id,
      name,
      label,
      ibot_node,
      session_id,
      device_status,
      active_channel,
      last_connect_timestamp
    )
  }

  public fun <T : Any> selectByDeviceId(device_id: String, mapper: (
    id: Long,
    device_id: String,
    name: String,
    label: String?,
    ibot_node: String?,
    session_id: String?,
    device_status: String?,
    active_channel: String?,
    last_connect_timestamp: String?,
  ) -> T): Query<T> = SelectByDeviceIdQuery(device_id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun selectByDeviceId(device_id: String): Query<Device> = selectByDeviceId(device_id) { id,
      device_id_, name, label, ibot_node, session_id, device_status, active_channel,
      last_connect_timestamp ->
    Device(
      id,
      device_id_,
      name,
      label,
      ibot_node,
      session_id,
      device_status,
      active_channel,
      last_connect_timestamp
    )
  }

  public fun insertDevice(
    device_id: String,
    name: String,
    label: String?,
    ibot_node: String?,
    session_id: String?,
    device_status: String?,
    active_channel: String?,
    last_connect_timestamp: String?,
  ) {
    driver.execute(1_769_523_376, """
        |INSERT OR IGNORE INTO Device(device_id, name, label, ibot_node, session_id, device_status,
        |                             active_channel, last_connect_timestamp)
        |VALUES (?,?,?,?,?,?,?,?)
        """.trimMargin(), 8) {
          bindString(0, device_id)
          bindString(1, name)
          bindString(2, label)
          bindString(3, ibot_node)
          bindString(4, session_id)
          bindString(5, device_status)
          bindString(6, active_channel)
          bindString(7, last_connect_timestamp)
        }
    notifyQueries(1_769_523_376) { emit ->
      emit("Device")
    }
  }

  public fun deleteAll() {
    driver.execute(-817_726_123, """DELETE FROM Device""", 0)
    notifyQueries(-817_726_123) { emit ->
      emit("Device")
    }
  }

  public fun deleteByDeviceid(device_id: String) {
    driver.execute(1_105_609_012, """DELETE FROM Device WHERE device_id = ?""", 1) {
          bindString(0, device_id)
        }
    notifyQueries(1_105_609_012) { emit ->
      emit("Device")
    }
  }

  public fun upsert(
    name: String,
    label: String?,
    ibot_node: String?,
    session_id: String?,
    device_status: String?,
    active_channel: String?,
    last_connect_timestamp: String?,
    device_id: String,
  ) {
    transaction {
      driver.execute(1_463_781_345, """
          |UPDATE Device
          |    SET name = ?,
          |    label = ?,
          |    ibot_node = ?,
          |    session_id = ?,
          |    device_status = ?,
          |    active_channel = ?,
          |    last_connect_timestamp = ?
          |    WHERE device_id = ?
          """.trimMargin(), 8) {
            bindString(0, name)
            bindString(1, label)
            bindString(2, ibot_node)
            bindString(3, session_id)
            bindString(4, device_status)
            bindString(5, active_channel)
            bindString(6, last_connect_timestamp)
            bindString(7, device_id)
          }
      driver.execute(1_463_781_346, """
          |INSERT OR IGNORE INTO Device (device_id, name, label, ibot_node, session_id, device_status,
          |                                  active_channel, last_connect_timestamp)
          |    VALUES (?,?,?,?,?,
          |    ?,?,?)
          """.trimMargin(), 8) {
            bindString(0, device_id)
            bindString(1, name)
            bindString(2, label)
            bindString(3, ibot_node)
            bindString(4, session_id)
            bindString(5, device_status)
            bindString(6, active_channel)
            bindString(7, last_connect_timestamp)
          }
    }
    notifyQueries(676_382_768) { emit ->
      emit("Device")
    }
  }

  private inner class SelectByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Device", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Device", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(1_294_757_231, """SELECT * FROM Device WHERE id = ?""", mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "Device.sq:selectById"
  }

  private inner class SelectByDeviceIdQuery<out T : Any>(
    public val device_id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Device", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Device", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_341_342_875, """SELECT * FROM Device WHERE device_id = ?""", mapper,
        1) {
      bindString(0, device_id)
    }

    override fun toString(): String = "Device.sq:selectByDeviceId"
  }
}
