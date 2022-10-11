package org.xgvela.oam.tubevnf;

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
    comments = "Source: tubevnf.proto")
public final class TubeVnfServiceGrpc {

  private TubeVnfServiceGrpc() {}

  public static final String SERVICE_NAME = "tubevnf.TubeVnfService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<VnfReq,
      VnfResp> METHOD_TUBE_VNF =
      io.grpc.MethodDescriptor.<VnfReq, VnfResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "tubevnf.TubeVnfService", "TubeVnf"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              VnfReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              VnfResp.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<VnfReq,
      VnfResp> METHOD_DE_TUBE_VNF =
      io.grpc.MethodDescriptor.<VnfReq, VnfResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "tubevnf.TubeVnfService", "DeTubeVnf"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              VnfReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              VnfResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TubeVnfServiceStub newStub(io.grpc.Channel channel) {
    return new TubeVnfServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TubeVnfServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TubeVnfServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TubeVnfServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TubeVnfServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TubeVnfServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void tubeVnf(VnfReq request,
                        io.grpc.stub.StreamObserver<VnfResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TUBE_VNF, responseObserver);
    }

    /**
     */
    public void deTubeVnf(VnfReq request,
                          io.grpc.stub.StreamObserver<VnfResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DE_TUBE_VNF, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_TUBE_VNF,
            asyncUnaryCall(
              new MethodHandlers<
                VnfReq,
                VnfResp>(
                  this, METHODID_TUBE_VNF)))
          .addMethod(
            METHOD_DE_TUBE_VNF,
            asyncUnaryCall(
              new MethodHandlers<
                VnfReq,
                VnfResp>(
                  this, METHODID_DE_TUBE_VNF)))
          .build();
    }
  }

  /**
   */
  public static final class TubeVnfServiceStub extends io.grpc.stub.AbstractStub<TubeVnfServiceStub> {
    private TubeVnfServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TubeVnfServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TubeVnfServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TubeVnfServiceStub(channel, callOptions);
    }

    /**
     */
    public void tubeVnf(VnfReq request,
                        io.grpc.stub.StreamObserver<VnfResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TUBE_VNF, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deTubeVnf(VnfReq request,
                          io.grpc.stub.StreamObserver<VnfResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DE_TUBE_VNF, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TubeVnfServiceBlockingStub extends io.grpc.stub.AbstractStub<TubeVnfServiceBlockingStub> {
    private TubeVnfServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TubeVnfServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TubeVnfServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TubeVnfServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public VnfResp tubeVnf(VnfReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TUBE_VNF, getCallOptions(), request);
    }

    /**
     */
    public VnfResp deTubeVnf(VnfReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DE_TUBE_VNF, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TubeVnfServiceFutureStub extends io.grpc.stub.AbstractStub<TubeVnfServiceFutureStub> {
    private TubeVnfServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TubeVnfServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TubeVnfServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TubeVnfServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<VnfResp> tubeVnf(
        VnfReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TUBE_VNF, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<VnfResp> deTubeVnf(
        VnfReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DE_TUBE_VNF, getCallOptions()), request);
    }
  }

  private static final int METHODID_TUBE_VNF = 0;
  private static final int METHODID_DE_TUBE_VNF = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TubeVnfServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TubeVnfServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TUBE_VNF:
          serviceImpl.tubeVnf((VnfReq) request,
              (io.grpc.stub.StreamObserver<VnfResp>) responseObserver);
          break;
        case METHODID_DE_TUBE_VNF:
          serviceImpl.deTubeVnf((VnfReq) request,
              (io.grpc.stub.StreamObserver<VnfResp>) responseObserver);
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

  private static final class TubeVnfServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return TubeVnf.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TubeVnfServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TubeVnfServiceDescriptorSupplier())
              .addMethod(METHOD_TUBE_VNF)
              .addMethod(METHOD_DE_TUBE_VNF)
              .build();
        }
      }
    }
    return result;
  }
}
