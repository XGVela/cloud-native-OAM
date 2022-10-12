package org.xgvela.oam.syncconfig;

import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: syncconfig.proto")
public final class SyncConfigServiceGrpc {

  private SyncConfigServiceGrpc() {}

  public static final String SERVICE_NAME = "syncconfig.SyncConfigService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<GetFileReq,
          GetFileResp> METHOD_GET_CONFIG_FILE =
      io.grpc.MethodDescriptor.<GetFileReq, GetFileResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "syncconfig.SyncConfigService", "GetConfigFile"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GetFileReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GetFileResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SyncConfigServiceStub newStub(io.grpc.Channel channel) {
    return new SyncConfigServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SyncConfigServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SyncConfigServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SyncConfigServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SyncConfigServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SyncConfigServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getConfigFile(GetFileReq request,
                              io.grpc.stub.StreamObserver<GetFileResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_CONFIG_FILE, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_CONFIG_FILE,
            asyncServerStreamingCall(
              new MethodHandlers<
                      GetFileReq,
                      GetFileResp>(
                  this, METHODID_GET_CONFIG_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class SyncConfigServiceStub extends io.grpc.stub.AbstractStub<SyncConfigServiceStub> {
    private SyncConfigServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SyncConfigServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SyncConfigServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SyncConfigServiceStub(channel, callOptions);
    }

    /**
     */
    public void getConfigFile(GetFileReq request,
                              io.grpc.stub.StreamObserver<GetFileResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_CONFIG_FILE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SyncConfigServiceBlockingStub extends io.grpc.stub.AbstractStub<SyncConfigServiceBlockingStub> {
    private SyncConfigServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SyncConfigServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SyncConfigServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SyncConfigServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<GetFileResp> getConfigFile(
        GetFileReq request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_CONFIG_FILE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SyncConfigServiceFutureStub extends io.grpc.stub.AbstractStub<SyncConfigServiceFutureStub> {
    private SyncConfigServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SyncConfigServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SyncConfigServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SyncConfigServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_CONFIG_FILE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SyncConfigServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SyncConfigServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CONFIG_FILE:
          serviceImpl.getConfigFile((GetFileReq) request,
              (io.grpc.stub.StreamObserver<GetFileResp>) responseObserver);
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

  private static final class SyncConfigServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SyncConfig.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SyncConfigServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SyncConfigServiceDescriptorSupplier())
              .addMethod(METHOD_GET_CONFIG_FILE)
              .build();
        }
      }
    }
    return result;
  }
}
