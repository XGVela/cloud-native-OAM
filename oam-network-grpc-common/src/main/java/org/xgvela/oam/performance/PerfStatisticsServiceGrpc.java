package org.xgvela.oam.performance;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: performance.proto")
public final class PerfStatisticsServiceGrpc {

  private PerfStatisticsServiceGrpc() {}

  public static final String SERVICE_NAME = "performance.PerfStatisticsService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<StatsInfoReq,
      StatsInfoRsp> METHOD_STATISTICS_REQ =
      io.grpc.MethodDescriptor.<StatsInfoReq, StatsInfoRsp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "performance.PerfStatisticsService", "StatisticsReq"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              StatsInfoReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              StatsInfoRsp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PerfStatisticsServiceStub newStub(io.grpc.Channel channel) {
    return new PerfStatisticsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PerfStatisticsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PerfStatisticsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PerfStatisticsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PerfStatisticsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PerfStatisticsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void statisticsReq(StatsInfoReq request,
                              io.grpc.stub.StreamObserver<StatsInfoRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STATISTICS_REQ, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_STATISTICS_REQ,
            asyncServerStreamingCall(
              new MethodHandlers<
                StatsInfoReq,
                StatsInfoRsp>(
                  this, METHODID_STATISTICS_REQ)))
          .build();
    }
  }

  /**
   */
  public static final class PerfStatisticsServiceStub extends io.grpc.stub.AbstractStub<PerfStatisticsServiceStub> {
    private PerfStatisticsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PerfStatisticsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected PerfStatisticsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PerfStatisticsServiceStub(channel, callOptions);
    }

    /**
     */
    public void statisticsReq(StatsInfoReq request,
                              io.grpc.stub.StreamObserver<StatsInfoRsp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_STATISTICS_REQ, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PerfStatisticsServiceBlockingStub extends io.grpc.stub.AbstractStub<PerfStatisticsServiceBlockingStub> {
    private PerfStatisticsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PerfStatisticsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected PerfStatisticsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PerfStatisticsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<StatsInfoRsp> statisticsReq(
        StatsInfoReq request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_STATISTICS_REQ, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PerfStatisticsServiceFutureStub extends io.grpc.stub.AbstractStub<PerfStatisticsServiceFutureStub> {
    private PerfStatisticsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PerfStatisticsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected PerfStatisticsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PerfStatisticsServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STATISTICS_REQ = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PerfStatisticsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PerfStatisticsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STATISTICS_REQ:
          serviceImpl.statisticsReq((StatsInfoReq) request,
              (io.grpc.stub.StreamObserver<StatsInfoRsp>) responseObserver);
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

  private static final class PerfStatisticsServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return performance.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PerfStatisticsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PerfStatisticsServiceDescriptorSupplier())
              .addMethod(METHOD_STATISTICS_REQ)
              .build();
        }
      }
    }
    return result;
  }
}
