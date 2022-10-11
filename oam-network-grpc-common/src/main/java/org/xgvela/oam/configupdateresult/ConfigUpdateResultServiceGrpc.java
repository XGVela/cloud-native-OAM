package org.xgvela.oam.configupdateresult;

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
    comments = "Source: configupdateresult.proto")
public final class ConfigUpdateResultServiceGrpc {

  private ConfigUpdateResultServiceGrpc() {}

  public static final String SERVICE_NAME = "configupdateresult.ConfigUpdateResultService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<CfgResultNotifyReq,
          CfgResultNotifyResp> METHOD_CFG_UPDATE_RESULT =
      io.grpc.MethodDescriptor.<CfgResultNotifyReq, CfgResultNotifyResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "configupdateresult.ConfigUpdateResultService", "cfgUpdateResult"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              CfgResultNotifyReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              CfgResultNotifyResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigUpdateResultServiceStub newStub(io.grpc.Channel channel) {
    return new ConfigUpdateResultServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigUpdateResultServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConfigUpdateResultServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigUpdateResultServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConfigUpdateResultServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ConfigUpdateResultServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void cfgUpdateResult(CfgResultNotifyReq request,
                                io.grpc.stub.StreamObserver<CfgResultNotifyResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CFG_UPDATE_RESULT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CFG_UPDATE_RESULT,
            asyncUnaryCall(
              new MethodHandlers<
                      CfgResultNotifyReq,
                      CfgResultNotifyResp>(
                  this, METHODID_CFG_UPDATE_RESULT)))
          .build();
    }
  }

  /**
   */
  public static final class ConfigUpdateResultServiceStub extends io.grpc.stub.AbstractStub<ConfigUpdateResultServiceStub> {
    private ConfigUpdateResultServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigUpdateResultServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigUpdateResultServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigUpdateResultServiceStub(channel, callOptions);
    }

    /**
     */
    public void cfgUpdateResult(CfgResultNotifyReq request,
                                io.grpc.stub.StreamObserver<CfgResultNotifyResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CFG_UPDATE_RESULT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ConfigUpdateResultServiceBlockingStub extends io.grpc.stub.AbstractStub<ConfigUpdateResultServiceBlockingStub> {
    private ConfigUpdateResultServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigUpdateResultServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigUpdateResultServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigUpdateResultServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public CfgResultNotifyResp cfgUpdateResult(CfgResultNotifyReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CFG_UPDATE_RESULT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ConfigUpdateResultServiceFutureStub extends io.grpc.stub.AbstractStub<ConfigUpdateResultServiceFutureStub> {
    private ConfigUpdateResultServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConfigUpdateResultServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ConfigUpdateResultServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConfigUpdateResultServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<CfgResultNotifyResp> cfgUpdateResult(
        CfgResultNotifyReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CFG_UPDATE_RESULT, getCallOptions()), request);
    }
  }

  private static final int METHODID_CFG_UPDATE_RESULT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConfigUpdateResultServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConfigUpdateResultServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CFG_UPDATE_RESULT:
          serviceImpl.cfgUpdateResult((CfgResultNotifyReq) request,
              (io.grpc.stub.StreamObserver<CfgResultNotifyResp>) responseObserver);
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

  private static final class ConfigUpdateResultServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ConfigUpdateResult.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConfigUpdateResultServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigUpdateResultServiceDescriptorSupplier())
              .addMethod(METHOD_CFG_UPDATE_RESULT)
              .build();
        }
      }
    }
    return result;
  }
}
