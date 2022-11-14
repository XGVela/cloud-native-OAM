package org.xgvela.oam.resource;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: resource.proto")
public final class SyncResServiceGrpc {

  private SyncResServiceGrpc() {}

  public static final String SERVICE_NAME = "resource.SyncResService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<GetFileReq,
      GetFileResp> METHOD_GET_RES_FILE =
      io.grpc.MethodDescriptor.<GetFileReq, GetFileResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "resource.SyncResService", "GetResFile"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GetFileReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GetFileResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SyncResServiceStub newStub(io.grpc.Channel channel) {
    return new SyncResServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SyncResServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SyncResServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SyncResServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SyncResServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SyncResServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getResFile(GetFileReq request,
                           io.grpc.stub.StreamObserver<GetFileResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_RES_FILE, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_RES_FILE,
            asyncServerStreamingCall(
              new MethodHandlers<
                GetFileReq,
                GetFileResp>(
                  this, METHODID_GET_RES_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class SyncResServiceStub extends io.grpc.stub.AbstractStub<SyncResServiceStub> {
    private SyncResServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SyncResServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SyncResServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SyncResServiceStub(channel, callOptions);
    }

    /**
     */
    public void getResFile(GetFileReq request,
                           io.grpc.stub.StreamObserver<GetFileResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_RES_FILE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SyncResServiceBlockingStub extends io.grpc.stub.AbstractStub<SyncResServiceBlockingStub> {
    private SyncResServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SyncResServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SyncResServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SyncResServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<GetFileResp> getResFile(
        GetFileReq request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_RES_FILE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SyncResServiceFutureStub extends io.grpc.stub.AbstractStub<SyncResServiceFutureStub> {
    private SyncResServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SyncResServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SyncResServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SyncResServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_RES_FILE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SyncResServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SyncResServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RES_FILE:
          serviceImpl.getResFile((GetFileReq) request,
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

  private static final class SyncResServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return resource.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SyncResServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SyncResServiceDescriptorSupplier())
              .addMethod(METHOD_GET_RES_FILE)
              .build();
        }
      }
    }
    return result;
  }
}
