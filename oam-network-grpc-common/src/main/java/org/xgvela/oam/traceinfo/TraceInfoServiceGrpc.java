package org.xgvela.oam.traceinfo;

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
    comments = "Source: traceinfo.proto")
public final class TraceInfoServiceGrpc {

  private TraceInfoServiceGrpc() {}

  public static final String SERVICE_NAME = "traceinfo.TraceInfoService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<TraceInfoReq,
      TraceInfoResp> METHOD_INFORM_TRACE_INFO =
      io.grpc.MethodDescriptor.<TraceInfoReq, TraceInfoResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "traceinfo.TraceInfoService", "InformTraceInfo"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              TraceInfoReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              TraceInfoResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TraceInfoServiceStub newStub(io.grpc.Channel channel) {
    return new TraceInfoServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TraceInfoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TraceInfoServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TraceInfoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TraceInfoServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TraceInfoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void informTraceInfo(TraceInfoReq request,
                                io.grpc.stub.StreamObserver<TraceInfoResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INFORM_TRACE_INFO, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_INFORM_TRACE_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                TraceInfoReq,
                TraceInfoResp>(
                  this, METHODID_INFORM_TRACE_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class TraceInfoServiceStub extends io.grpc.stub.AbstractStub<TraceInfoServiceStub> {
    private TraceInfoServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TraceInfoServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TraceInfoServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TraceInfoServiceStub(channel, callOptions);
    }

    /**
     */
    public void informTraceInfo(TraceInfoReq request,
                                io.grpc.stub.StreamObserver<TraceInfoResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INFORM_TRACE_INFO, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TraceInfoServiceBlockingStub extends io.grpc.stub.AbstractStub<TraceInfoServiceBlockingStub> {
    private TraceInfoServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TraceInfoServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TraceInfoServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TraceInfoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public TraceInfoResp informTraceInfo(TraceInfoReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INFORM_TRACE_INFO, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TraceInfoServiceFutureStub extends io.grpc.stub.AbstractStub<TraceInfoServiceFutureStub> {
    private TraceInfoServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TraceInfoServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TraceInfoServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TraceInfoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<TraceInfoResp> informTraceInfo(
        TraceInfoReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INFORM_TRACE_INFO, getCallOptions()), request);
    }
  }

  private static final int METHODID_INFORM_TRACE_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TraceInfoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TraceInfoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INFORM_TRACE_INFO:
          serviceImpl.informTraceInfo((TraceInfoReq) request,
              (io.grpc.stub.StreamObserver<TraceInfoResp>) responseObserver);
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

  private static final class TraceInfoServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return TraceInfo.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TraceInfoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TraceInfoServiceDescriptorSupplier())
              .addMethod(METHOD_INFORM_TRACE_INFO)
              .build();
        }
      }
    }
    return result;
  }
}
