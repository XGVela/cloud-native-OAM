package org.xgvela.oam.configrestore;

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
    comments = "Source: configrestore.proto")
public final class ConfigRestoreServiceGrpc {

  private ConfigRestoreServiceGrpc() {}

  public static final String SERVICE_NAME = "configrestore.ConfigRestoreService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<NotifyCfgReq,
      NotifyCfgResp> METHOD_NOTIFY_UPDATE_CFG =
      io.grpc.MethodDescriptor.<NotifyCfgReq, NotifyCfgResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "configrestore.ConfigRestoreService", "notifyUpdateCfg"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              NotifyCfgReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              NotifyCfgResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigRestoreServiceStub newStub(io.grpc.Channel channel) {
    return new ConfigRestoreServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigRestoreServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConfigRestoreServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigRestoreServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConfigRestoreServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ConfigRestoreServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * notify omc download config file
     * </pre>
     */
    public void notifyUpdateCfg(NotifyCfgReq request,
                                io.grpc.stub.StreamObserver<NotifyCfgResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_NOTIFY_UPDATE_CFG, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_NOTIFY_UPDATE_CFG,
            asyncUnaryCall(
              new MethodHandlers<
                NotifyCfgReq,
                NotifyCfgResp>(
                  this, METHODID_NOTIFY_UPDATE_CFG)))
          .build();
    }
  }

  /**
   */
  public static final class ConfigRestoreServiceStub extends io.grpc.stub.AbstractStub<ConfigRestoreServiceStub> {
    private ConfigRestoreServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigRestoreServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigRestoreServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigRestoreServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *notify omc download config file
     * </pre>
     */
    public void notifyUpdateCfg(NotifyCfgReq request,
                                io.grpc.stub.StreamObserver<NotifyCfgResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_NOTIFY_UPDATE_CFG, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ConfigRestoreServiceBlockingStub extends io.grpc.stub.AbstractStub<ConfigRestoreServiceBlockingStub> {
    private ConfigRestoreServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigRestoreServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigRestoreServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigRestoreServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *notify omc download config file
     * </pre>
     */
    public NotifyCfgResp notifyUpdateCfg(NotifyCfgReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_NOTIFY_UPDATE_CFG, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ConfigRestoreServiceFutureStub extends io.grpc.stub.AbstractStub<ConfigRestoreServiceFutureStub> {
    private ConfigRestoreServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigRestoreServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigRestoreServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigRestoreServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *notify omc download config file
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<NotifyCfgResp> notifyUpdateCfg(
        NotifyCfgReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_NOTIFY_UPDATE_CFG, getCallOptions()), request);
    }
  }

  private static final int METHODID_NOTIFY_UPDATE_CFG = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConfigRestoreServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConfigRestoreServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NOTIFY_UPDATE_CFG:
          serviceImpl.notifyUpdateCfg((NotifyCfgReq) request,
              (io.grpc.stub.StreamObserver<NotifyCfgResp>) responseObserver);
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

  private static final class ConfigRestoreServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ConfigRestore.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConfigRestoreServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigRestoreServiceDescriptorSupplier())
              .addMethod(METHOD_NOTIFY_UPDATE_CFG)
              .build();
        }
      }
    }
    return result;
  }
}
