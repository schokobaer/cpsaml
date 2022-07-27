

export interface UrlPath {
    main: "ADMIN" | "DASHBOARD"
    dashboard?: string
    instance?: string
}

export function getUrlPath(location: Location): UrlPath {
    let parts = location.hash.split("/")
    const urlPath: UrlPath = {
        main: parts[1] === "admin" ? "ADMIN" : "DASHBOARD",
        dashboard: parts[2],
        instance: parts[3]
    }
    return urlPath
}

export function navigate(location: Location, urlpath: UrlPath) {
    let path = "#/" + urlpath.main.toLowerCase()
    if (urlpath.dashboard) {
        path += "/" + urlpath.dashboard
        if (urlpath.instance) {
            path += "/" + urlpath.instance
        }
    }
    location.hash = path
}