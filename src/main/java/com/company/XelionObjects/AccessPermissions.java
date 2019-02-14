package com.company.XelionObjects;

import com.google.gson.annotations.SerializedName;

/**
 * The access permissions of this object.
 */
public enum AccessPermissions
{
    /**
     * The object may not accessed by others. This value can be set when the object is created. Naturally, objects that are sent to the client cannot have this permissions.
     */
    @SerializedName("NoAccess")NO_ACCESS,

    /**
     * Read access, no changes permitted.
     */
    @SerializedName("Read")READ,

    /**
     * The object may be changed but not removed.
     */
    @SerializedName("ReadWrite")READ_WRITE,

    /**
     * The object may be changed or removed.
     */
    @SerializedName("ReadWriteRemove")READ_WRITE_REMOVE,
}