import {CPS} from "../dto/Cps";
import {CpsComponent, Property} from "./MetaObjectModel";

export const metaDataMap: Map<CPS, CpsComponent> = new Map<CPS, CpsComponent>()

const roombaStruct: Array<Property> = [
    {
        name: "id",
        type: "primitive",
        dataType: "STRING"
    },
    {
        name: "state",
        type: "enum",
        literals: [ 'DOCKED', 'NAVIGATING', 'TASK', 'IDLE', 'SEEKING', 'MANUAL' ]
    }
	,
    {
        name: "tasks",
        list: true,
        type: "complex",
        properties: [
        	{
        	    name: "angle",
        	    type: "primitive",
        	    dataType: "FLOAT",
        	    		bounds: {
        	    			min: -360.0,
        	    			max: 360.0
        	    		}
        	},
        	{
        	    name: "time",
        	    type: "primitive",
        	    dataType: "FLOAT",
        	    		bounds: {
        	    			min: 0.0,
        	    			max: 255.0
        	    		}
        	},
        	{
        	    name: "speed",
        	    type: "primitive",
        	    dataType: "INT",
        	    		bounds: {
        	    			min: -200.0,
        	    			max: 200.0
        	    		}
        	}
        ]
    }
    ,
    {
        name: 'wheels',
        type: "complex",
        properties: [
            {
                name: "lifted",
                type: "primitive",
                dataType: "BOOL"
            },
            {
                name: "speed",
                type: "complex",
                properties: [
                	{
                	    name: "left",
                	    type: "primitive",
                	    dataType: "INT",
                	    		bounds: {
                	    			min: -200.0,
                	    			max: 200.0
                	    		}
                	},
                	{
                	    name: "right",
                	    type: "primitive",
                	    dataType: "INT",
                	    		bounds: {
                	    			min: -200.0,
                	    			max: 200.0
                	    		}
                	}
                ]
            }
        ]
    },    {
        name: 'speaker',
        type: "complex",
        properties: [
            {
                name: "playing",
                type: "primitive",
                dataType: "BOOL"
            }
        ]
    },    {
        name: 'navigator',
        type: "complex",
        properties: [
            {
                name: "orientating",
                type: "primitive",
                dataType: "BOOL"
            },
            {
                name: "position",
                type: "primitive",
                dataType: "STRING"
            },
            {
                name: "target",
                type: "primitive",
                dataType: "STRING"
            }
        ]
    },    {
        name: 'battery',
        type: "complex",
        properties: [
            {
                name: "docked",
                type: "primitive",
                dataType: "BOOL"
            },
            {
                name: "energy",
                type: "primitive",
                dataType: "FLOAT",
                		bounds: {
                			min: 0.0,
                			max: 1.0
                		}
            }
        ]
    },    {
        name: 'bumper',
        type: "complex",
        properties: [
            {
                name: "pushed",
                type: "primitive",
                dataType: "BOOL"
            }
        ]
    }
]

// ITS
metaDataMap.set("ITS", {
    services: [
    	{
    	        name: 'request',
    	        parameters: [
    	{
    	    name: 'target',
    	    optional: false,
    	    type: "STRING"
    	}
    	        ]
    	    }
    ],
    properties: [
        {
            name: 'roombas',
            list: true,
            type: "complex",
            properties: roombaStruct
        }
    ]
})

metaDataMap.set("ROOMBA", {
    services: [
        {
                name: 'drive',
                parameters: [
        {
            name: 'speed',
            optional: false,
            type: "INT"
        }
                ]
            },
        {
                name: 'turn',
                parameters: [
        {
            name: 'angle',
            optional: false,
            type: "FLOAT"
        }
                ]
            },
        {
                name: 'stop',
                parameters: [
                ]
            },
        {
                name: 'stack',
                parameters: [
        {
            name: 'speed',
            optional: false,
            type: "INT"
        },
        {
            name: 'time',
            optional: false,
            type: "FLOAT"
        },
        {
            name: 'angle',
            optional: false,
            type: "FLOAT"
        }
                ]
            },
        {
                name: 'start',
                parameters: [
                ]
            },
        {
                name: 'clear',
                parameters: [
                ]
            },
        {
                name: 'dock',
                parameters: [
                ]
            },
        {
                name: 'navigate',
                parameters: [
        {
            name: 'target',
            optional: false,
            type: "STRING"
        }
                ]
            }
    ],
    properties: roombaStruct
})
