const zipCodesData = {
    AL: ['36003', '36006', '36008', '36051', '36066', '36067'],
    AK: [],
    AZ: [],
    AR: [],
    CA: [],
    CO: [],
    CT: [],
    DE: [],
    DC: [],
    FL: [],
    GA: [],
    HI: [],
    ID: [],
    IL: [],
    IN: [],
    IA: [],
    KS: [],
    KY: [],
    LA: [],
    ME: [],
    MD: [],
    MA: [],
    MI: [],
    MN: [],
    MS: [],
    MO: [],
    MT: [],
    NE: [],
    NV: [],
    NH: [],
    NJ: [],
    NM: [],
    NY: [],
    NC: [],
    ND: [],
    OH: [],
    OK: [],
    OR: [],
    PA: [],
    RI: [],
    SC: [],
    SD: [],
    TN: [],
    TX: [],
    UT: [],
    VT: [],
    VA: [],
    WA: [],
    WV: [],
    WI: [],
    WY: []
}

export default function handler(req, res) {
    const { state } = req.query;

    if (state && zipCodesData[state]) {
        const zipCodes = zipCodesData[state];
        res.status(200).json({ zipCodes });
    } else {
        res.status(200).json({ zipCodes: [] });
    }
}