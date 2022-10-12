package org.xgvela.oam.configuremanagerwithfile;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: configuremanagerwithfile.proto")
public final class ConfigureManagerWithFileServiceGrpc {

  private ConfigureManagerWithFileServiceGrpc() {}

  public static final String SERVICE_NAME = "configuremanagerwithfile.ConfigureManagerWithFileService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<UpdateCfgFileWithFileReq,
          UpdateCfgWithFileRsp> METHOD_UPDATE_CONFIG_WITH_FILE =
      io.grpc.MethodDescriptor.<UpdateCfgFileWithFileReq, UpdateCfgWithFileRsp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "configuremanagerwithfile.ConfigureManagerWithFileService", "UpdateConfigWithFile"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgFileWithFileReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgWithFileRsp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigureManagerWithFileServiceStub newStub(io.grpc.Channel channel) {
    return new ConfigureManagerWithFileServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigureManagerWithFileServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConfigureManagerWithFileServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigureManagerWithFileServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConfigureManagerWithFileServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ConfigureManagerWithFileServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<UpdateCfgFileWithFileReq> updateConfigWithFile(
        io.grpc.stub.StreamObserver<UpdateCfgWithFileRsp> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_UPDATE_CONFIG_WITH_FILE, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_UPDATE_CONFIG_WITH_FILE,
            asyncClientStreamingCall(
              new MethodHandlers<
                      UpdateCfgFileWithFileReq,
                      UpdateCfgWithFileRsp>(
                  this, METHODID_UPDATE_CONFIG_WITH_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class ConfigureManagerWithFileServiceStub extends io.grpc.stub.AbstractStub<ConfigureManagerWithFileServiceStub> {
    private ConfigureManagerWithFileServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigureManagerWithFileServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigureManagerWithFileServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigureManagerWithFileServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<UpdateCfgFileWithFileReq> updateConfigWithFile(
        io.grpc.stub.StreamObserver<UpdateCfgWithFileRsp> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_UPDATE_CONFIG_WITH_FILE, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ConfigureManagerWithFileServiceBlockingStub extends io.grpc.stub.AbstractStub<ConfigureManagerWithFileServiceBlockingStub> {
    private ConfigureManagerWithFileServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigureManagerWithFileServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigureManagerWithFileServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigureManagerWithFileServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class ConfigureManagerWithFileServiceFutureStub extends io.grpc.stub.AbstractStub<ConfigureManagerWithFileServiceFutureStub> {
    private ConfigureManagerWithFileServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigureManagerWithFileServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigureManagerWithFileServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigureManagerWithFileServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPDATE_CONFIG_WITH_FILE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConfigureManagerWithFileServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConfigureManagerWithFileServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_CONFIG_WITH_FILE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.updateConfigWithFile(
              (io.grpc.stub.StreamObserver<UpdateCfgWithFileRsp>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ConfigureManagerWithFileServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ConfigureManagerWithFile.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConfigureManagerWithFileServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigureManagerWithFileServiceDescriptorSupplier())
              .addMethod(METHOD_UPDATE_CONFIG_WITH_FILE)
              .build();
        }
      }
    }
    return result;
  }
}
