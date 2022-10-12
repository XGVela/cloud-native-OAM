package org.xgvela.oam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.xgvela.oam.entity.conf.OamVnfConfigFile;

import java.util.List;

/**
 * <p>
 *
 * </p>
 */
public interface IOamVnfConfigFileService extends IService<OamVnfConfigFile> {

    List<OamVnfConfigFile> confInfo(String neId, String neType, String vnfName);

    boolean confDelivery(OamVnfConfigFile.VnfConfigDeliveryRequest request);

    boolean confSwitch(OamVnfConfigFile.VnfConfigDeliveryRequest request);

    boolean confResult(OamVnfConfigFile.ConfUpdateRequest request);

    boolean notifyDownLoadFiles(OamVnfConfigFile.Vnf request);

    boolean cfgUpdateResultNotify(OamVnfConfigFile.VnfRequest request);

    boolean deleteFileAndTask(String id);

}
