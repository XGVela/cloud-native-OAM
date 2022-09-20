package org.xgvela.oam.nfregister;

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
    comments = "Source: nfregister.proto")
public final class NfRegisterServiceGrpc {

  private NfRegisterServiceGrpc() {}

  public static final String SERVICE_NAME = "nfregister.NfRegisterService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<RegisterReq,
      RegisterRsp> METHOD_NF_REGISTER =
      io.grpc.MethodDescriptor.<RegisterReq, RegisterRsp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "nfregister.NfRegisterService", "NfRegister"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              RegisterReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              RegisterRsp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NfRegisterServiceStub newStub(io.grpc.Channel channel) {
    return new NfRegisterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NfRegisterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NfRegisterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NfRegisterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NfRegisterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NfRegisterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void nfRegister(RegisterReq request,
                           io.grpc.stub.StreamObserver<RegisterRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_NF_REGISTER, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_NF_REGISTER,
            asyncUnaryCall(
              new MethodHandlers<
                RegisterReq,
                RegisterRsp>(
                  this, METHODID_NF_REGISTER)))
          .build();
    }
  }

  /**
   */
  public static final class NfRegisterServiceStub extends io.grpc.stub.AbstractStub<NfRegisterServiceStub> {
    private NfRegisterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NfRegisterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NfRegisterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NfRegisterServiceStub(channel, callOptions);
    }

    /**
     */
    public void nfRegister(RegisterReq request,
                           io.grpc.stub.StreamObserver<RegisterRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_NF_REGISTER, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NfRegisterServiceBlockingStub extends io.grpc.stub.AbstractStub<NfRegisterServiceBlockingStub> {
    private NfRegisterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NfRegisterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NfRegisterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NfRegisterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public RegisterRsp nfRegister(RegisterReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_NF_REGISTER, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NfRegisterServiceFutureStub extends io.grpc.stub.AbstractStub<NfRegisterServiceFutureStub> {
    private NfRegisterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NfRegisterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NfRegisterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NfRegisterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RegisterRsp> nfRegister(
        RegisterReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_NF_REGISTER, getCallOptions()), request);
    }
  }

  private static final int METHODID_NF_REGISTER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NfRegisterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NfRegisterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NF_REGISTER:
          serviceImpl.nfRegister((RegisterReq) request,
              (io.grpc.stub.StreamObserver<RegisterRsp>) responseObserver);
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

  private static final class NfRegisterServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return NFRegister.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NfRegisterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NfRegisterServiceDescriptorSupplier())
              .addMethod(METHOD_NF_REGISTER)
              .build();
        }
      }
    }
    return result;
  }
}
