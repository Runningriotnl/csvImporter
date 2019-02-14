package com.company.XelionObjects;

public class XelionObject
    {
        /**
         * The unique object ID.
         */
        private String oid;

        /**
         * The object type.
         */
        private XelionObjectType objectType;

        /**
         * The change type. This attribute is used when the object is written to Xelion.
         */
        private ChangeType changeType;

        public String getOid()
        {
            return oid;
        }

        public XelionObject setOid(String oid)
        {
            this.oid = oid;
            return this;
        }

        public XelionObjectType getObjectType()
        {
            return objectType;
        }

        public XelionObject setObjectType(XelionObjectType objectType)
        {
            this.objectType = objectType;
            return this;
        }

        public ChangeType getChangeType()
        {
            return changeType;
        }

        public XelionObject setChangeType(ChangeType changeType)
        {
            this.changeType = changeType;
            return this;
        }

        @Override
        public String toString()
        {
            return "XelionObject{" + "oid='" + oid + '\'' + ", objectType=" + objectType + ", changeType=" + changeType + '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            XelionObject that = (XelionObject) o;

            if (oid != null ? !oid.equals(that.oid) : that.oid != null)
                return false;
            if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null)
                return false;
            return changeType != null ? changeType.equals(that.changeType) : that.changeType == null;
        }

        @Override
        public int hashCode()
        {
            int result = oid != null ? oid.hashCode() : 0;
            result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
            result = 31 * result + (changeType != null ? changeType.hashCode() : 0);
            return result;
        }
}
