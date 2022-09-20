package org.xgvela.oam.nftask;

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
    comments = "Source: nftask.proto")
public final class NfTaskServiceGrpc {

  private NfTaskServiceGrpc() {}

  public static final String SERVICE_NAME = "nftask.NfTaskService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<NfTaskReq,
      NfTaskResp> METHOD_CREATE_NF_TASK =
      io.grpc.MethodDescriptor.<NfTaskReq, NfTaskResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "nftask.NfTaskService", "createNfTask"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              NfTaskReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              NfTaskResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NfTaskServiceStub newStub(io.grpc.Channel channel) {
    return new NfTaskServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NfTaskServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NfTaskServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NfTaskServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NfTaskServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NfTaskServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createNfTask(NfTaskReq request,
                             io.grpc.stub.StreamObserver<NfTaskResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_NF_TASK, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CREATE_NF_TASK,
            asyncUnaryCall(
              new MethodHandlers<
                NfTaskReq,
                NfTaskResp>(
                  this, METHODID_CREATE_NF_TASK)))
          .build();
    }
  }

  /**
   */
  public static final class NfTaskServiceStub extends io.grpc.stub.AbstractStub<NfTaskServiceStub> {
    private NfTaskServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NfTaskServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NfTaskServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NfTaskServiceStub(channel, callOptions);
    }

    /**
     */
    public void createNfTask(NfTaskReq request,
                             io.grpc.stub.StreamObserver<NfTaskResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_NF_TASK, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NfTaskServiceBlockingStub extends io.grpc.stub.AbstractStub<NfTaskServiceBlockingStub> {
    private NfTaskServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NfTaskServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NfTaskServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NfTaskServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public NfTaskResp createNfTask(NfTaskReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_NF_TASK, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NfTaskServiceFutureStub extends io.grpc.stub.AbstractStub<NfTaskServiceFutureStub> {
    private NfTaskServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NfTaskServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NfTaskServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NfTaskServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<NfTaskResp> createNfTask(
        NfTaskReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_NF_TASK, getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_NF_TASK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NfTaskServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NfTaskServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_NF_TASK:
          serviceImpl.createNfTask((NfTaskReq) request,
              (io.grpc.stub.StreamObserver<NfTaskResp>) responseObserver);
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

  private static final class NfTaskServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return NFTask.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NfTaskServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NfTaskServiceDescriptorSupplier())
              .addMethod(METHOD_CREATE_NF_TASK)
              .build();
        }
      }
    }
    return result;
  }
}
