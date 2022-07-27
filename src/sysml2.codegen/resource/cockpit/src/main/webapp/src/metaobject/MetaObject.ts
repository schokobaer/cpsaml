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
        literals: ['IDLE', 'MANUAL', 'ORIENTATING', 'DOCKED', 'NAVIGATING']
    },
    {
        name: "job",
        type: "primitive",
        dataType: "STRING"
    },
    {
        name: "location",
        type: "primitive",
        dataType: "STRING"
    },
    {
        name: 'wheels',
        type: "complex",
        properties: [
            {
                name: 'grounded',
                type: "primitive",
                dataType: "BOOL"
            },
            {
                name: 'speed',
                type: "complex",
                properties: [
                    {
                        name: 'left',
                        type: "primitive",
                        dataType: "INT",
                        bounds: {
                            min: 0,
                            max: 200,
                            step: 1
                        }
                    },
                    {
                        name: 'right',
                        type: "primitive",
                        dataType: "INT",
                        bounds: {
                            min: 0,
                            max: 200,
                            step: 1
                        }
                    }
                ]
            }
        ]
    },
    {
        name: 'battery',
        type: "complex",
        properties: [
            {
                name: 'docked',
                type: "primitive",
                dataType: "BOOL"
            },
            {
                name: 'energy',
                type: "primitive",
                dataType: "FLOAT",
                bounds: {
                    min: 0.0,
                    max: 1.0,
                    step: 0.01,
                    digits: 2
                }
            }
        ]
    },
    {
        name: 'bumper',
        type: "complex",
        properties: [
            {
                name: 'bumped',
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
            name: 'order',
            parameters: [
                {
                    name: 'target',
                    optional: false,
                    type: "STRING"
                }
            ]
        },
        {
            name: 'turnall',
            parameters: []
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
                    type: "INT",
                    bounds: {
                        defaultValue: 50,
                        min: -200,
                        max: 200,
                        step: 10
                    }
                },
                {
                    name: 'duration',
                    optional: true,
                    type: "FLOAT",
                    bounds: {
                        min: 0,
                        max: 100,
                        step: 1
                    }
                }
            ]
        },
        {
            name: 'stop',
            parameters: []
        },
        {
            name: 'turn',
            parameters: [
                {
                    name: 'angle',
                    optional: false,
                    type: "INT",
                    bounds: {
                        defaultValue: 0,
                        min: -180,
                        max: 180,
                        step: 3
                    }
                }
            ]
        },
        {
            name: 'dock',
            parameters: []
        },
        {
            name: 'drive_to',
            parameters: [
                {
                    name: 'target',
                    type: "STRING",
                    optional: false
                }
            ]
        }
    ],
    properties: roombaStruct
})

