package org.xgvela.oam.configuremanager;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: configuremanager.proto")
public final class ConfigureManagerServiceGrpc {

  private ConfigureManagerServiceGrpc() {}

  public static final String SERVICE_NAME = "configuremanager.ConfigureManagerService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<UpdateCfgFileReq,
      UpdateCfgFileRsps> METHOD_UPDATE_CONFIG_FILE =
      io.grpc.MethodDescriptor.<UpdateCfgFileReq, UpdateCfgFileRsps>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "configuremanager.ConfigureManagerService", "updateConfigFile"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgFileReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgFileRsps.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigureManagerServiceStub newStub(io.grpc.Channel channel) {
    return new ConfigureManagerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigureManagerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConfigureManagerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigureManagerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConfigureManagerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ConfigureManagerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<UpdateCfgFileReq> updateConfigFile(
        io.grpc.stub.StreamObserver<UpdateCfgFileRsps> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_UPDATE_CONFIG_FILE, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_UPDATE_CONFIG_FILE,
            asyncClientStreamingCall(
              new MethodHandlers<
                UpdateCfgFileReq,
                UpdateCfgFileRsps>(
                  this, METHODID_UPDATE_CONFIG_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class ConfigureManagerServiceStub extends io.grpc.stub.AbstractStub<ConfigureManagerServiceStub> {
    private ConfigureManagerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigureManagerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigureManagerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigureManagerServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<UpdateCfgFileReq> updateConfigFile(
        io.grpc.stub.StreamObserver<UpdateCfgFileRsps> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_UPDATE_CONFIG_FILE, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ConfigureManagerServiceBlockingStub extends io.grpc.stub.AbstractStub<ConfigureManagerServiceBlockingStub> {
    private ConfigureManagerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigureManagerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigureManagerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigureManagerServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class ConfigureManagerServiceFutureStub extends io.grpc.stub.AbstractStub<ConfigureManagerServiceFutureStub> {
    private ConfigureManagerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigureManagerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigureManagerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigureManagerServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPDATE_CONFIG_FILE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConfigureManagerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConfigureManagerServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_UPDATE_CONFIG_FILE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.updateConfigFile(
              (io.grpc.stub.StreamObserver<UpdateCfgFileRsps>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ConfigureManagerServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ConfigureManager.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConfigureManagerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigureManagerServiceDescriptorSupplier())
              .addMethod(METHOD_UPDATE_CONFIG_FILE)
              .build();
        }
      }
    }
    return result;
  }
}
