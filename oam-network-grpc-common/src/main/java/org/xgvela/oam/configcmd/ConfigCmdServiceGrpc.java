package org.xgvela.oam.configcmd;

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
    comments = "Source: configcmd.proto")
public final class ConfigCmdServiceGrpc {

  private ConfigCmdServiceGrpc() {}

  public static final String SERVICE_NAME = "configcmd.ConfigCmdService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<UpdateCfgReq,
      UpdateCfgResp> METHOD_UPDATE_NFCFG =
      io.grpc.MethodDescriptor.<UpdateCfgReq, UpdateCfgResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "configcmd.ConfigCmdService", "updateNFcfg"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              UpdateCfgResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigCmdServiceStub newStub(io.grpc.Channel channel) {
    return new ConfigCmdServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigCmdServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConfigCmdServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigCmdServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConfigCmdServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ConfigCmdServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *omc修改nf配置文件后，通知CP-Agent配置文件变更
     * </pre>
     */
    public void updateNFcfg(UpdateCfgReq request,
                            io.grpc.stub.StreamObserver<UpdateCfgResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_NFCFG, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_UPDATE_NFCFG,
            asyncUnaryCall(
              new MethodHandlers<
                UpdateCfgReq,
                UpdateCfgResp>(
                  this, METHODID_UPDATE_NFCFG)))
          .build();
    }
  }

  /**
   */
  public static final class ConfigCmdServiceStub extends io.grpc.stub.AbstractStub<ConfigCmdServiceStub> {
    private ConfigCmdServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigCmdServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigCmdServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigCmdServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *omc修改nf配置文件后，通知CP-Agent配置文件变更
     * </pre>
     */
    public void updateNFcfg(UpdateCfgReq request,
                            io.grpc.stub.StreamObserver<UpdateCfgResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_NFCFG, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ConfigCmdServiceBlockingStub extends io.grpc.stub.AbstractStub<ConfigCmdServiceBlockingStub> {
    private ConfigCmdServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigCmdServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigCmdServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigCmdServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *omc修改nf配置文件后，通知CP-Agent配置文件变更
     * </pre>
     */
    public UpdateCfgResp updateNFcfg(UpdateCfgReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_NFCFG, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ConfigCmdServiceFutureStub extends io.grpc.stub.AbstractStub<ConfigCmdServiceFutureStub> {
    private ConfigCmdServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigCmdServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigCmdServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigCmdServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *omc修改nf配置文件后，通知CP-Agent配置文件变更
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<UpdateCfgResp> updateNFcfg(
        UpdateCfgReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_NFCFG, getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE_NFCFG = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConfigCmdServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConfigCmdServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_NFCFG:
          serviceImpl.updateNFcfg((UpdateCfgReq) request,
              (io.grpc.stub.StreamObserver<UpdateCfgResp>) responseObserver);
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

  private static final class ConfigCmdServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ConfigCmd.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConfigCmdServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigCmdServiceDescriptorSupplier())
              .addMethod(METHOD_UPDATE_NFCFG)
              .build();
        }
      }
    }
    return result;
  }
}
