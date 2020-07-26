package com.pudulabs.cloudrun.dataclean.service;

import com.pudulabs.cloudrun.dataclean.dto.DataCleanResponse;

public interface DataCleanService {

    DataCleanResponse cleanDataMultitenant(String tenant);

}
