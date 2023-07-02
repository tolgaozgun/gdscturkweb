export enum PermissionType {
    All = "All",
    Some = "Some",
    Own = "Own",
    None = "None",
}



const LeadPermissions = {
    "create": {

    },
    'edit': {
        
    },
    'view': {
        "profile": {
            "type": PermissionType.All,
        },
        "buddyTeam": {
            "type": PermissionType.All,
        },
    },
}
