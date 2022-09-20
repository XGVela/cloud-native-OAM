package org.xgvela.oam.alarm;

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
    comments = "Source: alarm.proto")
public final class AlarmReportServiceGrpc {

  private AlarmReportServiceGrpc() {}

  public static final String SERVICE_NAME = "alarm.AlarmReportService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<AlarmReq,
      AlarmRsp> METHOD_ALARM_REPORT =
      io.grpc.MethodDescriptor.<AlarmReq, AlarmRsp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "alarm.AlarmReportService", "AlarmReport"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AlarmReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AlarmRsp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AlarmReportServiceStub newStub(io.grpc.Channel channel) {
    return new AlarmReportServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AlarmReportServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AlarmReportServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AlarmReportServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AlarmReportServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AlarmReportServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void alarmReport(AlarmReq request,
                            io.grpc.stub.StreamObserver<AlarmRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ALARM_REPORT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ALARM_REPORT,
            asyncUnaryCall(
              new MethodHandlers<
                AlarmReq,
                AlarmRsp>(
                  this, METHODID_ALARM_REPORT)))
          .build();
    }
  }

  /**
   */
  public static final class AlarmReportServiceStub extends io.grpc.stub.AbstractStub<AlarmReportServiceStub> {
    private AlarmReportServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AlarmReportServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AlarmReportServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AlarmReportServiceStub(channel, callOptions);
    }

    /**
     */
    public void alarmReport(AlarmReq request,
                            io.grpc.stub.StreamObserver<AlarmRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ALARM_REPORT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AlarmReportServiceBlockingStub extends io.grpc.stub.AbstractStub<AlarmReportServiceBlockingStub> {
    private AlarmReportServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AlarmReportServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AlarmReportServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AlarmReportServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public AlarmRsp alarmReport(AlarmReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ALARM_REPORT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AlarmReportServiceFutureStub extends io.grpc.stub.AbstractStub<AlarmReportServiceFutureStub> {
    private AlarmReportServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AlarmReportServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AlarmReportServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AlarmReportServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AlarmRsp> alarmReport(
        AlarmReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ALARM_REPORT, getCallOptions()), request);
    }
  }

  private static final int METHODID_ALARM_REPORT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AlarmReportServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AlarmReportServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ALARM_REPORT:
          serviceImpl.alarmReport((AlarmReq) request,
              (io.grpc.stub.StreamObserver<AlarmRsp>) responseObserver);
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

  private static final class AlarmReportServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return alarm.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AlarmReportServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AlarmReportServiceDescriptorSupplier())
              .addMethod(METHOD_ALARM_REPORT)
              .build();
        }
      }
    }
    return result;
  }
}
