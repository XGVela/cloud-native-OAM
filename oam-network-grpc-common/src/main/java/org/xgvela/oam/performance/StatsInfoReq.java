// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: performance.proto

package org.xgvela.oam.performance;

/**
 * Protobuf type {@code performance.StatsInfoReq}
 */
public  final class StatsInfoReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:performance.StatsInfoReq)
    StatsInfoReqOrBuilder {
  // Use StatsInfoReq.newBuilder() to construct.
  private StatsInfoReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StatsInfoReq() {
    neId_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private StatsInfoReq(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              statsResultInfo_ = com.google.protobuf.MapField.newMapField(
                  StatsResultInfoDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<String, Double>
            statsResultInfo__ = input.readMessage(
                StatsResultInfoDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            statsResultInfo_.getMutableMap().put(
                statsResultInfo__.getKey(), statsResultInfo__.getValue());
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
    return performance.internal_static_performance_StatsInfoReq_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 1:
        return internalGetStatsResultInfo();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return performance.internal_static_performance_StatsInfoReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            StatsInfoReq.class, StatsInfoReq.Builder.class);
  }

  private int bitField0_;
  public static final int STATSRESULTINFO_FIELD_NUMBER = 1;
  private static final class StatsResultInfoDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        String, Double> defaultEntry =
            com.google.protobuf.MapEntry
            .<String, Double>newDefaultInstance(
                performance.internal_static_performance_StatsInfoReq_StatsResultInfoEntry_descriptor,
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.DOUBLE,
                0D);
  }
  private com.google.protobuf.MapField<
      String, Double> statsResultInfo_;
  private com.google.protobuf.MapField<String, Double>
  internalGetStatsResultInfo() {
    if (statsResultInfo_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          StatsResultInfoDefaultEntryHolder.defaultEntry);
    }
    return statsResultInfo_;
  }

  public int getStatsResultInfoCount() {
    return internalGetStatsResultInfo().getMap().size();
  }
  /**
   * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
   */

  public boolean containsStatsResultInfo(
      String key) {
    if (key == null) { throw new NullPointerException(); }
    return internalGetStatsResultInfo().getMap().containsKey(key);
  }
  /**
   * Use {@link #getStatsResultInfoMap()} instead.
   */
  @Deprecated
  public java.util.Map<String, Double> getStatsResultInfo() {
    return getStatsResultInfoMap();
  }
  /**
   * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
   */

  public java.util.Map<String, Double> getStatsResultInfoMap() {
    return internalGetStatsResultInfo().getMap();
  }
  /**
   * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
   */

  public double getStatsResultInfoOrDefault(
      String key,
      double defaultValue) {
    if (key == null) { throw new NullPointerException(); }
    java.util.Map<String, Double> map =
        internalGetStatsResultInfo().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
   */

  public double getStatsResultInfoOrThrow(
      String key) {
    if (key == null) { throw new NullPointerException(); }
    java.util.Map<String, Double> map =
        internalGetStatsResultInfo().getMap();
    if (!map.containsKey(key)) {
      throw new IllegalArgumentException();
    }
    return map.get(key);
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
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetStatsResultInfo(),
        StatsResultInfoDefaultEntryHolder.defaultEntry,
        1);
    if (!getNeIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, neId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (java.util.Map.Entry<String, Double> entry
         : internalGetStatsResultInfo().getMap().entrySet()) {
      com.google.protobuf.MapEntry<String, Double>
      statsResultInfo__ = StatsResultInfoDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, statsResultInfo__);
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
    if (!(obj instanceof StatsInfoReq)) {
      return super.equals(obj);
    }
    StatsInfoReq other = (StatsInfoReq) obj;

    boolean result = true;
    result = result && internalGetStatsResultInfo().equals(
        other.internalGetStatsResultInfo());
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
    if (!internalGetStatsResultInfo().getMap().isEmpty()) {
      hash = (37 * hash) + STATSRESULTINFO_FIELD_NUMBER;
      hash = (53 * hash) + internalGetStatsResultInfo().hashCode();
    }
    hash = (37 * hash) + NEID_FIELD_NUMBER;
    hash = (53 * hash) + getNeId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static StatsInfoReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static StatsInfoReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static StatsInfoReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static StatsInfoReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static StatsInfoReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static StatsInfoReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static StatsInfoReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static StatsInfoReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static StatsInfoReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static StatsInfoReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static StatsInfoReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static StatsInfoReq parseFrom(
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
  public static Builder newBuilder(StatsInfoReq prototype) {
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
   * Protobuf type {@code performance.StatsInfoReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:performance.StatsInfoReq)
      StatsInfoReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return performance.internal_static_performance_StatsInfoReq_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetStatsResultInfo();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMutableStatsResultInfo();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return performance.internal_static_performance_StatsInfoReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              StatsInfoReq.class, StatsInfoReq.Builder.class);
    }

    // Construct using StatsInfoReq.newBuilder()
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
      internalGetMutableStatsResultInfo().clear();
      neId_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return performance.internal_static_performance_StatsInfoReq_descriptor;
    }

    public StatsInfoReq getDefaultInstanceForType() {
      return StatsInfoReq.getDefaultInstance();
    }

    public StatsInfoReq build() {
      StatsInfoReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public StatsInfoReq buildPartial() {
      StatsInfoReq result = new StatsInfoReq(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.statsResultInfo_ = internalGetStatsResultInfo();
      result.statsResultInfo_.makeImmutable();
      result.neId_ = neId_;
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof StatsInfoReq) {
        return mergeFrom((StatsInfoReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(StatsInfoReq other) {
      if (other == StatsInfoReq.getDefaultInstance()) return this;
      internalGetMutableStatsResultInfo().mergeFrom(
          other.internalGetStatsResultInfo());
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
      StatsInfoReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (StatsInfoReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.MapField<
        String, Double> statsResultInfo_;
    private com.google.protobuf.MapField<String, Double>
    internalGetStatsResultInfo() {
      if (statsResultInfo_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            StatsResultInfoDefaultEntryHolder.defaultEntry);
      }
      return statsResultInfo_;
    }
    private com.google.protobuf.MapField<String, Double>
    internalGetMutableStatsResultInfo() {
      onChanged();;
      if (statsResultInfo_ == null) {
        statsResultInfo_ = com.google.protobuf.MapField.newMapField(
            StatsResultInfoDefaultEntryHolder.defaultEntry);
      }
      if (!statsResultInfo_.isMutable()) {
        statsResultInfo_ = statsResultInfo_.copy();
      }
      return statsResultInfo_;
    }

    public int getStatsResultInfoCount() {
      return internalGetStatsResultInfo().getMap().size();
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */

    public boolean containsStatsResultInfo(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      return internalGetStatsResultInfo().getMap().containsKey(key);
    }
    /**
     * Use {@link #getStatsResultInfoMap()} instead.
     */
    @Deprecated
    public java.util.Map<String, Double> getStatsResultInfo() {
      return getStatsResultInfoMap();
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */

    public java.util.Map<String, Double> getStatsResultInfoMap() {
      return internalGetStatsResultInfo().getMap();
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */

    public double getStatsResultInfoOrDefault(
        String key,
        double defaultValue) {
      if (key == null) { throw new NullPointerException(); }
      java.util.Map<String, Double> map =
          internalGetStatsResultInfo().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */

    public double getStatsResultInfoOrThrow(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      java.util.Map<String, Double> map =
          internalGetStatsResultInfo().getMap();
      if (!map.containsKey(key)) {
        throw new IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearStatsResultInfo() {
      internalGetMutableStatsResultInfo().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */

    public Builder removeStatsResultInfo(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      internalGetMutableStatsResultInfo().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @Deprecated
    public java.util.Map<String, Double>
    getMutableStatsResultInfo() {
      return internalGetMutableStatsResultInfo().getMutableMap();
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */
    public Builder putStatsResultInfo(
        String key,
        double value) {
      if (key == null) { throw new NullPointerException(); }

      internalGetMutableStatsResultInfo().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, double&gt; statsResultInfo = 1;</code>
     */

    public Builder putAllStatsResultInfo(
        java.util.Map<String, Double> values) {
      internalGetMutableStatsResultInfo().getMutableMap()
          .putAll(values);
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


    // @@protoc_insertion_point(builder_scope:performance.StatsInfoReq)
  }

  // @@protoc_insertion_point(class_scope:performance.StatsInfoReq)
  private static final StatsInfoReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new StatsInfoReq();
  }

  public static StatsInfoReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StatsInfoReq>
      PARSER = new com.google.protobuf.AbstractParser<StatsInfoReq>() {
    public StatsInfoReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new StatsInfoReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StatsInfoReq> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<StatsInfoReq> getParserForType() {
    return PARSER;
  }

  public StatsInfoReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

