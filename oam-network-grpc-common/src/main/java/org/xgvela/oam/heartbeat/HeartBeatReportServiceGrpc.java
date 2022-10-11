package org.xgvela.oam.heartbeat;

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
    comments = "Source: heartbeat.proto")
public final class HeartBeatReportServiceGrpc {

  private HeartBeatReportServiceGrpc() {}

  public static final String SERVICE_NAME = "heartbeat.HeartBeatReportService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<HeartReq,
      HeartRsp> METHOD_HEART_BEAT_REPORT =
      io.grpc.MethodDescriptor.<HeartReq, HeartRsp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "heartbeat.HeartBeatReportService", "HeartBeatReport"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              HeartReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              HeartRsp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HeartBeatReportServiceStub newStub(io.grpc.Channel channel) {
    return new HeartBeatReportServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HeartBeatReportServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HeartBeatReportServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HeartBeatReportServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HeartBeatReportServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HeartBeatReportServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void heartBeatReport(HeartReq request,
                                io.grpc.stub.StreamObserver<HeartRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_HEART_BEAT_REPORT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_HEART_BEAT_REPORT,
            asyncUnaryCall(
              new MethodHandlers<
                HeartReq,
                HeartRsp>(
                  this, METHODID_HEART_BEAT_REPORT)))
          .build();
    }
  }

  /**
   */
  public static final class HeartBeatReportServiceStub extends io.grpc.stub.AbstractStub<HeartBeatReportServiceStub> {
    private HeartBeatReportServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeartBeatReportServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HeartBeatReportServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeartBeatReportServiceStub(channel, callOptions);
    }

    /**
     */
    public void heartBeatReport(HeartReq request,
                                io.grpc.stub.StreamObserver<HeartRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HEART_BEAT_REPORT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HeartBeatReportServiceBlockingStub extends io.grpc.stub.AbstractStub<HeartBeatReportServiceBlockingStub> {
    private HeartBeatReportServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeartBeatReportServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HeartBeatReportServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeartBeatReportServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public HeartRsp heartBeatReport(HeartReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HEART_BEAT_REPORT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HeartBeatReportServiceFutureStub extends io.grpc.stub.AbstractStub<HeartBeatReportServiceFutureStub> {
    private HeartBeatReportServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeartBeatReportServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HeartBeatReportServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeartBeatReportServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<HeartRsp> heartBeatReport(
        HeartReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HEART_BEAT_REPORT, getCallOptions()), request);
    }
  }

  private static final int METHODID_HEART_BEAT_REPORT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HeartBeatReportServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HeartBeatReportServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HEART_BEAT_REPORT:
          serviceImpl.heartBeatReport((HeartReq) request,
              (io.grpc.stub.StreamObserver<HeartRsp>) responseObserver);
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

  private static final class HeartBeatReportServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HeartBeat.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HeartBeatReportServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HeartBeatReportServiceDescriptorSupplier())
              .addMethod(METHOD_HEART_BEAT_REPORT)
              .build();
        }
      }
    }
    return result;
  }
}
