package com.example.soulfire_mobile.task

import com.example.soulfire_mobile.server.IAPICommand


interface ITaskFinishedHandler<T> {
    fun onTaskFinished(taskCode: Int, code: TaskResultCode?, result: T, tag: Any?)
    enum class TaskResultCode {
        Success, CancelledByUser, NoNetworkConnection, UnknownError;

        companion object {
            fun fromErrorCode(iApiCommandErrorCode: Int): TaskResultCode {
                return when (iApiCommandErrorCode) {
                    IAPICommand.ERROR_NONE -> Success
                    IAPICommand.ERROR_DEVICE_OFFLINE -> NoNetworkConnection
                    IAPICommand.ERROR_CANCELLED_BY_USER -> CancelledByUser
                    else -> UnknownError
                }
            }
        }
    }
}