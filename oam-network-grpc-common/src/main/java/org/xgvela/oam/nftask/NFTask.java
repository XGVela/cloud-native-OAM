// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: nftask.proto

package org.xgvela.oam.nftask;

public final class NFTask {
  private NFTask() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_nftask_NfTaskReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_nftask_NfTaskReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_nftask_NfTaskResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_nftask_NfTaskResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\014nftask.proto\022\006nftask\"\260\001\n\tNfTaskReq\022\016\n\006" +
      "taskId\030\001 \001(\r\022\020\n\010dataType\030\002 \001(\t\022\022\n\nuserNu" +
      "mber\030\003 \001(\t\022\016\n\006runNow\030\004 \001(\r\022\022\n\ntimeLength" +
      "\030\005 \001(\r\022\021\n\tstartTime\030\006 \001(\004\022\017\n\007endTime\030\007 \001" +
      "(\004\022\016\n\006status\030\010 \001(\r\022\025\n\rinterfaceType\030\t \003(" +
      "\t\"\034\n\nNfTaskResp\022\016\n\006result\030\001 \001(\t2H\n\rNfTas" +
      "kService\0227\n\014createNfTask\022\021.nftask.NfTask" +
      "Req\032\022.nftask.NfTaskResp\"\000B&\n\025com.inspur." +
      "oam.nftaskB\006NFTaskP\001\242\002\002NTb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_nftask_NfTaskReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_nftask_NfTaskReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_nftask_NfTaskReq_descriptor,
        new String[] { "TaskId", "DataType", "UserNumber", "RunNow", "TimeLength", "StartTime", "EndTime", "Status", "InterfaceType", });
    internal_static_nftask_NfTaskResp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_nftask_NfTaskResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_nftask_NfTaskResp_descriptor,
        new String[] { "Result", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
