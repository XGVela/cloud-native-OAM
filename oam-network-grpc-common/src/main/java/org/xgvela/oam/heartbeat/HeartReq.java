// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: heartbeat.proto

package org.xgvela.oam.heartbeat;

/**
 * Protobuf type {@code heartbeat.HeartReq}
 */
public  final class HeartReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:heartbeat.HeartReq)
    HeartReqOrBuilder {
  // Use HeartReq.newBuilder() to construct.
  private HeartReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeartReq() {
    neType_ = "";
    neId_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private HeartReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            String s = input.readStringRequireUtf8();

            neType_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            neId_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return HeartBeat.internal_static_heartbeat_HeartReq_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return HeartBeat.internal_static_heartbeat_HeartReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            HeartReq.class, Builder.class);
  }

  public static final int NETYPE_FIELD_NUMBER = 1;
  private volatile Object neType_;
  /**
   * <code>string neType = 1;</code>
   */
  public String getNeType() {
    Object ref = neType_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      neType_ = s;
      return s;
    }
  }
  /**
   * <code>string neType = 1;</code>
   */
  public com.google.protobuf.ByteString
      getNeTypeBytes() {
    Object ref = neType_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      neType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NEID_FIELD_NUMBER = 2;
  private volatile Object neId_;
  /**
   * <code>string neId = 2;</code>
   */
  public String getNeId() {
    Object ref = neId_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      neId_ = s;
      return s;
    }
  }
  /**
   * <code>string neId = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNeIdBytes() {
    Object ref = neId_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      neId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getNeTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, neType_);
    }
    if (!getNeIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, neId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getNeTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, neType_);
    }
    if (!getNeIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, neId_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof HeartReq)) {
      return super.equals(obj);
    }
    HeartReq other = (HeartReq) obj;

    boolean result = true;
    result = result && getNeType()
        .equals(other.getNeType());
    result = result && getNeId()
        .equals(other.getNeId());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NETYPE_FIELD_NUMBER;
    hash = (53 * hash) + getNeType().hashCode();
    hash = (37 * hash) + NEID_FIELD_NUMBER;
    hash = (53 * hash) + getNeId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static HeartReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static HeartReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static HeartReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static HeartReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static HeartReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static HeartReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static HeartReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static HeartReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static HeartReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static HeartReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static HeartReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static HeartReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(HeartReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code heartbeat.HeartReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:heartbeat.HeartReq)
      HeartReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return HeartBeat.internal_static_heartbeat_HeartReq_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return HeartBeat.internal_static_heartbeat_HeartReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              HeartReq.class, Builder.class);
    }

    // Construct using HeartReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      neType_ = "";

      neId_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return HeartBeat.internal_static_heartbeat_HeartReq_descriptor;
    }

    public HeartReq getDefaultInstanceForType() {
      return HeartReq.getDefaultInstance();
    }

    public HeartReq build() {
      HeartReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public HeartReq buildPartial() {
      HeartReq result = new HeartReq(this);
      result.neType_ = neType_;
      result.neId_ = neId_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof HeartReq) {
        return mergeFrom((HeartReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(HeartReq other) {
      if (other == HeartReq.getDefaultInstance()) return this;
      if (!other.getNeType().isEmpty()) {
        neType_ = other.neType_;
        onChanged();
      }
      if (!other.getNeId().isEmpty()) {
        neId_ = other.neId_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      HeartReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (HeartReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object neType_ = "";
    /**
     * <code>string neType = 1;</code>
     */
    public String getNeType() {
      Object ref = neType_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        neType_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string neType = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNeTypeBytes() {
      Object ref = neType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        neType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string neType = 1;</code>
     */
    public Builder setNeType(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      neType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string neType = 1;</code>
     */
    public Builder clearNeType() {
      
      neType_ = getDefaultInstance().getNeType();
      onChanged();
      return this;
    }
    /**
     * <code>string neType = 1;</code>
     */
    public Builder setNeTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      neType_ = value;
      onChanged();
      return this;
    }

    private Object neId_ = "";
    /**
     * <code>string neId = 2;</code>
     */
    public String getNeId() {
      Object ref = neId_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        neId_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string neId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNeIdBytes() {
      Object ref = neId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        neId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string neId = 2;</code>
     */
    public Builder setNeId(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      neId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string neId = 2;</code>
     */
    public Builder clearNeId() {
      
      neId_ = getDefaultInstance().getNeId();
      onChanged();
      return this;
    }
    /**
     * <code>string neId = 2;</code>
     */
    public Builder setNeIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      neId_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:heartbeat.HeartReq)
  }

  // @@protoc_insertion_point(class_scope:heartbeat.HeartReq)
  private static final HeartReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new HeartReq();
  }

  public static HeartReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<HeartReq>
      PARSER = new com.google.protobuf.AbstractParser<HeartReq>() {
    public HeartReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeartReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeartReq> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<HeartReq> getParserForType() {
    return PARSER;
  }

  public HeartReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

