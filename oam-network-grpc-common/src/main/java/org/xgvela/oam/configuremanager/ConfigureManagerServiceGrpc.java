package org.xgvela.oam.configuremanager;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

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
          UpdateCfgFileRsp> METHOD_UPDATE_CONFIG_FILE =
      io.grpc.MethodDescriptor.<UpdateCfgFileReq, UpdateCfgFileRsp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "configuremanager.ConfigureManagerService", "UpdateConfigFile"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgFileReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgFileRsp.getDefaultInstance()))
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
    public void updateConfigFile(UpdateCfgFileReq request,
                                 io.grpc.stub.StreamObserver<UpdateCfgFileRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_CONFIG_FILE, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_UPDATE_CONFIG_FILE,
            asyncUnaryCall(
              new MethodHandlers<
                      UpdateCfgFileReq,
                      UpdateCfgFileRsp>(
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
    public void updateConfigFile(UpdateCfgFileReq request,
                                 io.grpc.stub.StreamObserver<UpdateCfgFileRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_CONFIG_FILE, getCallOptions()), request, responseObserver);
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

    /**
     */
    public UpdateCfgFileRsp updateConfigFile(UpdateCfgFileReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_CONFIG_FILE, getCallOptions(), request);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UpdateCfgFileRsp> updateConfigFile(
        UpdateCfgFileReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_CONFIG_FILE, getCallOptions()), request);
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
        case METHODID_UPDATE_CONFIG_FILE:
          serviceImpl.updateConfigFile((UpdateCfgFileReq) request,
              (io.grpc.stub.StreamObserver<UpdateCfgFileRsp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
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
