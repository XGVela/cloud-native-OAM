// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: configuremanager.proto

package org.xgvela.oam.configuremanager;

public final class ConfigureManager {
  private ConfigureManager() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_configuremanager_UpdateCfgFileReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_configuremanager_UpdateCfgFileReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_configuremanager_UpdateCfgFileRsp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_configuremanager_UpdateCfgFileRsp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\026configuremanager.proto\022\020configuremanag" +
      "er\"R\n\020UpdateCfgFileReq\022\016\n\006nfType\030\001 \001(\t\022\014" +
      "\n\004neId\030\002 \001(\t\022\016\n\006taskId\030\003 \001(\t\022\020\n\010fileName" +
      "\030\004 \001(\t\"%\n\020UpdateCfgFileRsp\022\021\n\tupdateRsp\030" +
      "\001 \001(\t2w\n\027ConfigureManagerService\022\\\n\020Upda" +
      "teConfigFile\022\".configuremanager.UpdateCf" +
      "gFileReq\032\".configuremanager.UpdateCfgFil" +
      "eRsp\"\000B:\n\037org.xgvela.oam.configuremanage" +
      "rB\020ConfigureManagerP\001\242\002\002CMb\006proto3"
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
    internal_static_configuremanager_UpdateCfgFileReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_configuremanager_UpdateCfgFileReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_configuremanager_UpdateCfgFileReq_descriptor,
        new String[] { "NfType", "NeId", "TaskId", "FileName", });
    internal_static_configuremanager_UpdateCfgFileRsp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_configuremanager_UpdateCfgFileRsp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_configuremanager_UpdateCfgFileRsp_descriptor,
        new String[] { "UpdateRsp", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
