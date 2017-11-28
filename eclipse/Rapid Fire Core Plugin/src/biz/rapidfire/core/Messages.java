/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "biz.rapidfire.core.messages"; //$NON-NLS-1$

    public static final String EMPTY = ""; //$NON-NLS-1$
    public static final String SPACE = " "; //$NON-NLS-1$

    public static String E_R_R_O_R;

    // Dialog titles
    public static String DialogTitle_Specify_a_filter;
    public static String DialogTitle_Job;
    public static String DialogTitle_File;
    public static String DialogTitle_Library;
    public static String DialogTitle_Library_List;
    public static String DialogTitle_Notification;
    public static String DialogTitle_Area;
    public static String DialogTitle_Activity_Schedule;

    // Dialog action titles
    public static String DialogMode_CREATE;
    public static String DialogMode_COPY;
    public static String DialogMode_CHANGE;
    public static String DialogMode_DELETE;
    public static String DialogMode_DISPLAY;

    // Column headings
    public static String ColumnLabel_File;
    public static String ColumnLabel_Library;
    public static String ColumnLabel_Records_in_production_library;
    public static String ColumnLabel_Records_in_shadow_library;
    public static String ColumnLabel_Records_to_copy;
    public static String ColumnLabel_Records_copied;
    public static String ColumnLabel_Estimated_time;
    public static String ColumnLabel_Changes_to_apply;
    public static String ColumnLabel_Changes_applied;
    public static String ColumnLabel_Progress;
    public static String ColumnLabel_Active;
    public static String ColumnLabel_Start_time;
    public static String ColumnLabel_End_time;

    // Dialog labels
    public static String Label_E_Mail_colon;
    public static String Label_Internet_colon;
    public static String Label_Telefax_colon;
    public static String Label_Telefon_colon;

    public static String Label_Maintain_ativity_status_usage_info;

    public static String Label_Library_colon;
    public static String Label_Job_colon;
    public static String Label_Description_colon;
    public static String Label_Create_environment_colon;
    public static String Label_Job_queue_name_colon;
    public static String Label_Job_queue_library_name_colon;
    public static String Label_Status_colon;
    public static String Label_Full_generic_string;
    public static String Label_Enable_large_progress_bar;
    public static String Label_Position_colon;
    public static String Label_File_colon;
    public static String Label_FileType_colon;
    public static String Label_Copy_program_name_colon;
    public static String Label_Copy_program_library_name_colon;
    public static String Label_Conversion_program_name_colon;
    public static String Label_Conversion_program_library_name_colon;
    public static String Label_Shadow_library_colon;
    public static String Label_Library_list_colon;
    public static String Label_NotificationType_colon;
    public static String Label_User_colon;
    public static String Label_Message_queue_name_colon;
    public static String Label_Message_queue_library_name_colon;
    public static String Label_Area_colon;
    public static String Label_Area_library_colon;
    public static String Label_Area_library_list_colon;
    public static String Label_Area_library_ccsid;
    public static String Label_Command_extension_colon;
    public static String Label_Field_to_convert_colon;
    public static String Label_Rename_field_in_old_file_to_colon;
    public static String Label_Conversions_colon;
    public static String Label_Command_type_colon;
    public static String Label_Command_sequence_colon;
    public static String Label_Command_command_colon;

    public static String Tooltip_Library;
    public static String Tooltip_Job;
    public static String Tooltip_Description;
    public static String Tooltip_Create_environment;
    public static String Tooltip_Job_queue_name;
    public static String Tooltip_Job_queue_library_name;
    public static String Tooltip_Enable_large_progress_bar;
    public static String Tooltip_Position;
    public static String Tooltip_File;
    public static String Tooltip_FileType;
    public static String Tooltip_Copy_program_name;
    public static String Tooltip_Copy_program_library_name;
    public static String Tooltip_Conversion_program_name;
    public static String Tooltip_Conversion_program_library_name;
    public static String Tooltip_Shadow_library;
    public static String Tooltip_Library_list;
    public static String Tooltip_NotificationType;
    public static String Tooltip_User;
    public static String Tooltip_Message_queue_name;
    public static String Tooltip_Message_queue_library_name;
    public static String Tooltip_Area;
    public static String Tooltip_Area_library;
    public static String Tooltip_Area_library_list;
    public static String Tooltip_Area_library_ccsid;
    public static String Tooltip_Command_extension;
    public static String Tooltip_Field_to_convert;
    public static String Tooltip_Rename_field_in_old_file_to;
    public static String Tooltip_Conversions;
    public static String Tooltip_Command_type;
    public static String Tooltip_Command_sequence;
    public static String Tooltip_Command_command;

    // Action labels
    public static String ActionLabel_Auto_refresh_menu_item;
    public static String ActionLabel_Auto_refresh_menu_item_stop;
    public static String ActionLabel_Auto_refresh_menu_item_every_A_seconds;
    public static String ActionLabel_Enable_activity_time_frame;
    public static String ActionLabel_Disable_activity_time_frame;

    public static String ActionTooltip_Refresh;
    public static String ActionTooltip_Auto_refresh_menu_item_every_A_seconds;
    public static String ActionTooltip_Auto_refresh_menu_item_stop;
    public static String ActionTooltip_Enable_activity_time_frame;
    public static String ActionTooltip_Disable_activity_time_frame;

    // Property labels
    public static String Batch_job_name;
    public static String Batch_job_user;
    public static String Batch_job_number;

    public static String Tooltip_Batch_job_name;
    public static String Tooltip_Batch_job_user;
    public static String Tooltip_Batch_job_number;

    // Job labels
    public static String JobLabel_Refreshing_file_copy_statuses;

    // Error messages
    public static String Job_name_A_is_not_valid;
    public static String Description_A_is_not_valid;
    public static String Library_name_A_is_not_valid;
    public static String Create_environment_value_has_been_rejected;
    public static String Job_queue_name_A_is_not_valid;
    public static String The_library_name_must_be_specified;
    public static String The_job_name_must_be_specified;
    public static String The_job_status_must_be_specified;
    public static String Rapid_Fire_library_A_on_system_B_is_of_version_C_but_at_least_version_D_is_required_Please_update_the_Rapid_Fire_library;
    public static String The_installed_Rapid_Fire_plug_in_version_A_is_outdated_because_the_installed_Rapid_Fire_library_requires_at_least_version_B_of_the_Rapid_Fire_plug_in_Please_update_your_Rapid_Fire_plug_in;
    public static String Rapid_Fire_library_A_does_not_exist_on_system_B_Please_install_Rapid_Fire_library_A_on_system_B;
    public static String The_specified_library_A_on_system_B_is_not_a_Rapid_Fire_library;
    public static String Could_not_initialize_job_manager_for_job_A_in_library_B;
    public static String Could_not_initialize_file_manager_for_file_at_position_C_of_job_A_in_library_B;
    public static String File_position_A_is_not_valid;
    public static String File_name_A_is_not_valid;
    public static String File_type_A_is_not_valid;
    public static String Copy_program_name_A_is_not_valid;
    public static String Conversion_program_name_A_is_not_valid;
    public static String Could_not_initialize_library_manager_for_library_C_of_job_A_in_library_B;
    public static String Could_not_initialize_library_list_manager_for_library_list_C_of_job_A_in_library_B;
    public static String Could_not_start_a_Rapid_Fire_JDBC_connection;
    public static String Could_not_stop_the_Rapid_Fire_JDBC_connection;
    public static String Could_not_initialize_notification_manager_for_notification_at_position_C_of_job_A_in_library_B;
    public static String Name_of_library_A_is_not_valid;
    public static String Name_of_shadow_library_A_is_not_valid;
    public static String Library_list_name_A_is_not_valid;
    public static String Library_list_description_A_is_not_valid;
    public static String Invalid_sequence_number_A;
    public static String Notification_position_A_is_not_valid;
    public static String Notification_type_A_is_not_valid;
    public static String User_name_A_is_not_valid;
    public static String Message_queue_name_A_is_not_valid;
    public static String Could_not_initialize_area_manager_for_area_D_of_file_C_of_job_A_in_library_B;
    public static String Area_name_A_is_not_valid;
    public static String Ccsid_A_is_not_valid;
    public static String Command_extension_A_is_not_valid;
    public static String Could_not_initialize_conversion_manager_for_field_D_of_file_C_of_job_A_in_library_B;
    public static String Field_name_A_is_not_valid;
    public static String Field_names_must_not_match;
    public static String Conversion_statement_is_missing;
    public static String Could_not_initialize_command_manager_for_command_type_D_and_sequence_E_of_file_C_of_job_A_in_library_B;

    // API error messages
    public static String EntityManager_Unknown_error_code_A;

    public static String RapidFire_Start_001;

    public static String RapidFire_Stop_001;

    public static String JobManager_001;
    public static String JobManager_002;
    public static String JobManager_003;

    public static String FileManager_001;
    public static String FileManager_002;
    public static String FileManager_003;

    public static String LibraryManager_001;
    public static String LibraryManager_002;
    public static String LibraryManager_003;

    public static String LibraryListManager_001;
    public static String LibraryListManager_002;
    public static String LibraryListManager_003;

    public static String NotificationManager_001;
    public static String NotificationManager_002;
    public static String NotificationManager_003;

    public static String AreaManager_001;
    public static String AreaManager_002;
    public static String AreaManager_003;
    public static String AreaManager_004;

    public static String ConversionManager_001;
    public static String ConversionManager_002;
    public static String ConversionManager_003;
    public static String ConversionManager_004;

    public static String CommandManager_001;
    public static String CommandManager_002;
    public static String CommandManager_003;
    public static String CommandManager_004;

    private Messages() {
    }

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String bindParameters(String message, Object... values) {

        List<Object> bindings = new LinkedList<Object>();
        for (Object value : values) {
            bindings.add(value);
        }

        return bind(message, bindings.toArray());
    }
}
