// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resource.proto

package org.xgvela.oam.resource;

/**
 * Protobuf type {@code resource.GetFileReq}
 */
public  final class GetFileReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:resource.GetFileReq)
    GetFileReqOrBuilder {
  // Use GetFileReq.newBuilder() to construct.
  private GetFileReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetFileReq() {
    nfType_ = "";
    neId_ = "";
    fileName_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GetFileReq(
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

            nfType_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            neId_ = s;
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            fileName_ = s;
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
    return resource.internal_static_resource_GetFileReq_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return resource.internal_static_resource_GetFileReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GetFileReq.class, Builder.class);
  }

  public static final int NFTYPE_FIELD_NUMBER = 1;
  private volatile Object nfType_;
  /**
   * <code>string nfType = 1;</code>
   */
  public String getNfType() {
    Object ref = nfType_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      nfType_ = s;
      return s;
    }
  }
  /**
   * <code>string nfType = 1;</code>
   */
  public com.google.protobuf.ByteString
      getNfTypeBytes() {
    Object ref = nfType_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      nfType_ = b;
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

  public static final int FILENAME_FIELD_NUMBER = 3;
  private volatile Object fileName_;
  /**
   * <code>string fileName = 3;</code>
   */
  public String getFileName() {
    Object ref = fileName_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      fileName_ = s;
      return s;
    }
  }
  /**
   * <code>string fileName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getFileNameBytes() {
    Object ref = fileName_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      fileName_ = b;
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
    if (!getNfTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, nfType_);
    }
    if (!getNeIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, neId_);
    }
    if (!getFileNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, fileName_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getNfTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, nfType_);
    }
    if (!getNeIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, neId_);
    }
    if (!getFileNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, fileName_);
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
    if (!(obj instanceof GetFileReq)) {
      return super.equals(obj);
    }
    GetFileReq other = (GetFileReq) obj;

    boolean result = true;
    result = result && getNfType()
        .equals(other.getNfType());
    result = result && getNeId()
        .equals(other.getNeId());
    result = result && getFileName()
        .equals(other.getFileName());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NFTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getNfType().hashCode();
    hash = (37 * hash) + NEID_FIELD_NUMBER;
    hash = (53 * hash) + getNeId().hashCode();
    hash = (37 * hash) + FILENAME_FIELD_NUMBER;
    hash = (53 * hash) + getFileName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GetFileReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetFileReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetFileReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetFileReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetFileReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetFileReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetFileReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetFileReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static GetFileReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static GetFileReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GetFileReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetFileReq parseFrom(
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
  public static Builder newBuilder(GetFileReq prototype) {
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
   * Protobuf type {@code resource.GetFileReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:resource.GetFileReq)
      GetFileReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return resource.internal_static_resource_GetFileReq_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return resource.internal_static_resource_GetFileReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GetFileReq.class, Builder.class);
    }

    // Construct using org.xgvela.oam.resource.GetFileReq.newBuilder()
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
      nfType_ = "";

      neId_ = "";

      fileName_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return resource.internal_static_resource_GetFileReq_descriptor;
    }

    public GetFileReq getDefaultInstanceForType() {
      return GetFileReq.getDefaultInstance();
    }

    public GetFileReq build() {
      GetFileReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public GetFileReq buildPartial() {
      GetFileReq result = new GetFileReq(this);
      result.nfType_ = nfType_;
      result.neId_ = neId_;
      result.fileName_ = fileName_;
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
      if (other instanceof GetFileReq) {
        return mergeFrom((GetFileReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GetFileReq other) {
      if (other == GetFileReq.getDefaultInstance()) return this;
      if (!other.getNfType().isEmpty()) {
        nfType_ = other.nfType_;
        onChanged();
      }
      if (!other.getNeId().isEmpty()) {
        neId_ = other.neId_;
        onChanged();
      }
      if (!other.getFileName().isEmpty()) {
        fileName_ = other.fileName_;
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
      GetFileReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GetFileReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object nfType_ = "";
    /**
     * <code>string nfType = 1;</code>
     */
    public String getNfType() {
      Object ref = nfType_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        nfType_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string nfType = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNfTypeBytes() {
      Object ref = nfType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        nfType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string nfType = 1;</code>
     */
    public Builder setNfType(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      nfType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string nfType = 1;</code>
     */
    public Builder clearNfType() {

      nfType_ = getDefaultInstance().getNfType();
      onChanged();
      return this;
    }
    /**
     * <code>string nfType = 1;</code>
     */
    public Builder setNfTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      nfType_ = value;
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

    private Object fileName_ = "";
    /**
     * <code>string fileName = 3;</code>
     */
    public String getFileName() {
      Object ref = fileName_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        fileName_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string fileName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getFileNameBytes() {
      Object ref = fileName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        fileName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string fileName = 3;</code>
     */
    public Builder setFileName(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      fileName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string fileName = 3;</code>
     */
    public Builder clearFileName() {

      fileName_ = getDefaultInstance().getFileName();
      onChanged();
      return this;
    }
    /**
     * <code>string fileName = 3;</code>
     */
    public Builder setFileNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      fileName_ = value;
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


    // @@protoc_insertion_point(builder_scope:resource.GetFileReq)
  }

  // @@protoc_insertion_point(class_scope:resource.GetFileReq)
  private static final GetFileReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GetFileReq();
  }

  public static GetFileReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetFileReq>
      PARSER = new com.google.protobuf.AbstractParser<GetFileReq>() {
    public GetFileReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetFileReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetFileReq> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GetFileReq> getParserForType() {
    return PARSER;
  }

  public GetFileReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
