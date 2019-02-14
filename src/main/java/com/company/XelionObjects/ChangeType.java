package com.company.XelionObjects;

import com.google.gson.annotations.SerializedName;

/**
 * The change type is used when the object is written to Xelion. It allows to write aggregated objects partially: a subset of the sub-objects is written.
 */
public enum ChangeType
{
    /**
     * No changes are made.
     */
    @SerializedName("Unmodified")UNMODIFIED,

    /**
     * This object is changed by the client.
     */
    @SerializedName("Modified")MODIFIED,

    /**
     * This object is created by the client.
     */
    @SerializedName("Created")CREATED,

    /**
     * This object is removed by the client.
     */
    @SerializedName("Removed")REMOVED,
}